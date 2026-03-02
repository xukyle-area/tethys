package salary;

import java.io.IOException;

/**
 * 中国工资模拟器
 * 计算五险一金、个人所得税及实际到手工资
 * 支持单月计算和年度累计预扣预缴
 */
public class Salary {
    // ========== 公积金配置 ==========
    public static final double GJJ_RATE = 0.12; // 公积金缴纳比例（5%-12%）
    public static final double GJJ_MAX_BASE = 35811; // 公积金最高缴费基数

    // ========== 社保配置（以北京为例） ==========
    public static final double PENSION_RATE_PERSONAL = 0.08; // 养老保险个人比例
    public static final double PENSION_RATE_COMPANY = 0.16; // 养老保险公司比例
    public static final double MEDICAL_RATE_PERSONAL = 0.02; // 医疗保险个人比例
    public static final double MEDICAL_RATE_COMPANY = 0.095; // 医疗保险公司比例
    public static final double MEDICAL_EXTRA = 3; // 医疗保险个人固定金额
    public static final double UNEMPLOYMENT_RATE_PERSONAL = 0.005; // 失业保险个人比例
    public static final double UNEMPLOYMENT_RATE_COMPANY = 0.005; // 失业保险公司比例
    public static final double INJURY_RATE_COMPANY = 0.004; // 工伤保险公司比例
    public static final double MATERNITY_RATE_COMPANY = 0.008; // 生育保险公司比例

    // ========== 社保基数上下限 ==========
    public static final double SOCIAL_INSURANCE_MIN_BASE = 6326; // 社保最低缴费基数
    public static final double SOCIAL_INSURANCE_MAX_BASE = 35283; // 社保最高缴费基数

    // ========== 个税配置 ==========
    public static final double TAX_THRESHOLD = 5000; // 个税起征点

    // 个税累进税率表 [下限, 上限, 税率, 速算扣除数]
    private static final double[][] TAX_BRACKETS = {{0, 36000, 0.03, 0}, {36000, 144000, 0.10, 2520},
            {144000, 300000, 0.20, 16920}, {300000, 420000, 0.25, 31920}, {420000, 660000, 0.30, 52920},
            {660000, 960000, 0.35, 85920}, {960000, Double.MAX_VALUE, 0.45, 181920}};

    // ========== 实例变量 ==========
    private double specialDeduction; // 每月专项附加扣除
    private MonthData[] months = new MonthData[12]; // 12个月的数据
    private double bonusMonths = 0; // 年终奖月数（月base的倍数）

    public MonthData[] getMonthDatas() {
        return months;
    }

    /**
    * 构造函数 - 创建年度工资记录
    * @param specialDeduction 每月专项附加扣除
    */
    public Salary(double monthlySalary, double specialDeduction, double months) {
        this.specialDeduction = specialDeduction;
        this.bonusMonths = months;
        for (int i = 1; i <= 12; i++) {
            this.setMonth(i, monthlySalary, monthlySalary, monthlySalary);
        }
    }

    // ========== 月度数据录入 ==========

    /**
     * 设置某月工资
     * @param month 月份（1-12）
     * @param grossSalary 税前工资
     * @param socialBase 社保基数
     * @param gjjBase 公积金基数
     */
    public void setMonth(int month, double grossSalary, double socialBase, double gjjBase) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("月份必须在1-12之间");
        }
        double actualSocialBase = Math.min(Math.max(socialBase, SOCIAL_INSURANCE_MIN_BASE), SOCIAL_INSURANCE_MAX_BASE);
        double actualGjjBase = Math.min(Math.max(gjjBase, SOCIAL_INSURANCE_MIN_BASE), GJJ_MAX_BASE);
        months[month - 1] = new MonthData(grossSalary, actualSocialBase, actualGjjBase);
    }

    /** 检查某月是否有数据 */
    public boolean hasMonthData(int month) {
        return month >= 1 && month <= 12 && months[month - 1] != null;
    }

    /** 获取某月税前工资 */
    public double getGrossSalary(int month) {
        if (!hasMonthData(month))
            return 0;
        return months[month - 1].grossSalary;
    }

    // ========== 五险一金计算（某月） ==========

    /** 个人缴纳养老保险 */
    public double getPersonalPension(int month) {
        return getMonthData(month).socialBase * PENSION_RATE_PERSONAL;
    }

    /** 个人缴纳医疗保险 */
    public double getPersonalMedical(int month) {
        return getMonthData(month).socialBase * MEDICAL_RATE_PERSONAL + MEDICAL_EXTRA;
    }

    /** 个人缴纳失业保险 */
    public double getPersonalUnemployment(int month) {
        return getMonthData(month).socialBase * UNEMPLOYMENT_RATE_PERSONAL;
    }

    /** 个人缴纳公积金 */
    public double getPersonalGjj(int month) {
        return getMonthData(month).gjjBase * GJJ_RATE;
    }

    /** 个人缴纳五险合计（不含公积金） */
    public double getPersonalSocialInsurance(int month) {
        return getPersonalPension(month) + getPersonalMedical(month) + getPersonalUnemployment(month);
    }

    /** 个人缴纳五险一金合计 */
    public double getTotalPersonalDeduction(int month) {
        return getPersonalSocialInsurance(month) + getPersonalGjj(month);
    }

    /** 公司缴纳养老保险 */
    public double getCompanyPension(int month) {
        return getMonthData(month).socialBase * PENSION_RATE_COMPANY;
    }

    /** 公司缴纳医疗保险 */
    public double getCompanyMedical(int month) {
        return getMonthData(month).socialBase * MEDICAL_RATE_COMPANY;
    }

    /** 公司缴纳失业保险 */
    public double getCompanyUnemployment(int month) {
        return getMonthData(month).socialBase * UNEMPLOYMENT_RATE_COMPANY;
    }

    /** 公司缴纳工伤保险 */
    public double getCompanyInjury(int month) {
        return getMonthData(month).socialBase * INJURY_RATE_COMPANY;
    }

    /** 公司缴纳生育保险 */
    public double getCompanyMaternity(int month) {
        return getMonthData(month).socialBase * MATERNITY_RATE_COMPANY;
    }

    /** 公司缴纳公积金 */
    public double getCompanyGjj(int month) {
        return getMonthData(month).gjjBase * GJJ_RATE;
    }

    /** 公司缴纳五险合计（不含公积金） */
    public double getCompanySocialInsurance(int month) {
        return getCompanyPension(month) + getCompanyMedical(month) + getCompanyUnemployment(month)
                + getCompanyInjury(month) + getCompanyMaternity(month);
    }

    /** 公司缴纳五险一金合计 */
    public double getTotalCompanyDeduction(int month) {
        return getCompanySocialInsurance(month) + getCompanyGjj(month);
    }

    // ========== 个税计算（累计预扣预缴） ==========

    /** 获取当月应纳税所得额（不含五险一金后的部分） */
    public double getMonthlyTaxableIncome(int month) {
        MonthData data = getMonthData(month);
        double taxable = data.grossSalary - getTotalPersonalDeduction(month) - TAX_THRESHOLD - specialDeduction;
        return Math.max(0, taxable);
    }

    /** 累计应纳税所得额（截至指定月份） */
    public double getCumulativeTaxableIncome(int upToMonth) {
        double cumulative = 0;
        for (int i = 1; i <= upToMonth && i <= 12; i++) {
            if (months[i - 1] != null) {
                cumulative += getMonthlyTaxableIncome(i);
            }
        }
        return cumulative;
    }

    /** 根据累计所得计算累计应纳税额 */
    private double calculateCumulativeTax(double cumulativeIncome) {
        if (cumulativeIncome <= 0)
            return 0;
        for (double[] bracket : TAX_BRACKETS) {
            if (cumulativeIncome > bracket[0] && cumulativeIncome <= bracket[1]) {
                return cumulativeIncome * bracket[2] - bracket[3];
            }
        }
        return cumulativeIncome * 0.45 - 181920;
    }

    /** 累计应纳税额（截至指定月份） */
    public double getCumulativeTax(int upToMonth) {
        return calculateCumulativeTax(getCumulativeTaxableIncome(upToMonth));
    }

    /** 当月应预扣个税 */
    public double getMonthlyTax(int month) {
        if (month < 1 || month > 12 || months[month - 1] == null) {
            return 0;
        }
        double currentTax = getCumulativeTax(month);
        double previousTax = month > 1 ? getCumulativeTax(month - 1) : 0;
        return Math.max(0, currentTax - previousTax);
    }

    /** 当月实际到手工资 */
    public double getNetSalary(int month) {
        if (month < 1 || month > 12 || months[month - 1] == null) {
            return 0;
        }
        return months[month - 1].grossSalary - getTotalPersonalDeduction(month) - getMonthlyTax(month);
    }

    /** 当月公司用工成本 */
    public double getCompanyCost(int month) {
        if (months[month - 1] == null)
            return 0;
        return months[month - 1].grossSalary + getTotalCompanyDeduction(month);
    }

    // ========== 年终奖（单独计税） ==========

    /**
     * 设置年终奖月数
     * @param months 月base的倍数（如3.5代表3.5个月工资作为年终奖）
     */
    public Salary setBonus(double months) {

        return this;
    }

    /** 获取年终奖月数 */
    public double getBonusMonths() {
        return bonusMonths;
    }

    /** 获取年终奖基数（默认取12月工资，如无则取最后有数据的月份） */
    public double getBonusBase() {
        for (int i = 11; i >= 0; i--) {
            if (months[i] != null) {
                return months[i].grossSalary;
            }
        }
        return 0;
    }

    /** 获取年终奖金额 */
    public double getBonusAmount() {
        return getBonusBase() * bonusMonths;
    }

    /** 计算年终奖个税（单独计税） */
    public double getBonusTax() {
        return calculateBonusTax(getBonusAmount());
    }

    /** 年终奖到手金额 */
    public double getBonusNetAmount() {
        return getBonusAmount() - getBonusTax();
    }

    /** 静态方法：计算任意金额年终奖的个税 */
    public static double calculateBonusTax(double bonus) {
        if (bonus <= 0)
            return 0;
        double monthlyBonus = bonus / 12;
        double rate, quickDeduction;

        if (monthlyBonus <= 3000) {
            rate = 0.03;
            quickDeduction = 0;
        } else if (monthlyBonus <= 12000) {
            rate = 0.10;
            quickDeduction = 210;
        } else if (monthlyBonus <= 25000) {
            rate = 0.20;
            quickDeduction = 1410;
        } else if (monthlyBonus <= 35000) {
            rate = 0.25;
            quickDeduction = 2660;
        } else if (monthlyBonus <= 55000) {
            rate = 0.30;
            quickDeduction = 4410;
        } else if (monthlyBonus <= 80000) {
            rate = 0.35;
            quickDeduction = 7160;
        } else {
            rate = 0.45;
            quickDeduction = 15160;
        }
        return bonus * rate - quickDeduction;
    }

    // ========== 年度汇总 ==========

    /** 年度税前总收入 */
    public double getAnnualGrossSalary() {
        double total = 0;
        for (int i = 1; i <= 12; i++) {
            if (months[i - 1] != null) {
                total += months[i - 1].grossSalary;
            }
        }
        return total;
    }

    /** 年度五险一金（个人） */
    public double getAnnualPersonalDeduction() {
        double total = 0;
        for (int i = 1; i <= 12; i++) {
            if (months[i - 1] != null) {
                total += getTotalPersonalDeduction(i);
            }
        }
        return total;
    }

    /** 年度五险一金（公司） */
    public double getAnnualCompanyDeduction() {
        double total = 0;
        for (int i = 1; i <= 12; i++) {
            if (months[i - 1] != null) {
                total += getTotalCompanyDeduction(i);
            }
        }
        return total;
    }

    /** 年度公积金合计（个人+公司） */
    public double getAnnualGjj() {
        double total = 0;
        for (int i = 1; i <= 12; i++) {
            if (months[i - 1] != null) {
                total += getPersonalGjj(i) + getCompanyGjj(i);
            }
        }
        return total;
    }

    /** 年度个税合计 */
    public double getAnnualTax() {
        return getCumulativeTax(12);
    }

    /** 年度实际到手（含年终奖） */
    public double getAnnualNetSalary() {
        return getAnnualGrossSalary() - getAnnualPersonalDeduction() - getAnnualTax() + getBonusNetAmount();
    }

    /** 年度税前总收入（含年终奖） */
    public double getAnnualTotalIncome() {
        return getAnnualGrossSalary() + getBonusAmount();
    }

    /** 年度总个税（工资+年终奖） */
    public double getAnnualTotalTax() {
        return getAnnualTax() + getBonusTax();
    }

    /** 公司年度用工成本（含年终奖） */
    public double getAnnualCompanyCost() {
        return getAnnualGrossSalary() + getAnnualCompanyDeduction() + getBonusAmount();
    }

    // ========== 辅助方法 ==========

    private MonthData getMonthData(int month) {
        if (month < 1 || month > 12 || months[month - 1] == null) {
            throw new IllegalArgumentException("月份 " + month + " 无数据");
        }
        return months[month - 1];
    }

    /** 月度数据内部类 */
    private static class MonthData {
        double grossSalary;
        double socialBase;
        double gjjBase;

        MonthData(double grossSalary, double socialBase, double gjjBase) {
            this.grossSalary = grossSalary;
            this.socialBase = socialBase;
            this.gjjBase = gjjBase;
        }
    }

    // ========== 测试主函数 ==========
    public static void main(String[] args) throws IOException {
        Salary salary = new Salary(50_000, 2000, 3);
        HtmlGenerater.generateHtmlReport(salary);
    }
}
