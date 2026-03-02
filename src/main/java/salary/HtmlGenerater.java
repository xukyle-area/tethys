package salary;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class HtmlGenerater {
    private static final String[] MONTH_NAMES =
            {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    /** 生成HTML报告文件 */
    private static void generateHtmlReport(Salary salary, String filePath) throws IOException {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        StringBuilder html = new StringBuilder();

        // HTML头部和样式
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"zh-CN\">\n<head>\n");
        html.append("<meta charset=\"UTF-8\">\n");
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("<title>年度工资报告</title>\n");
        html.append("<style>\n");
        html.append(
                "body { font-family: 'Microsoft YaHei', 'PingFang SC', sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; padding: 20px; margin: 0; }\n");
        html.append(".container { max-width: 1200px; margin: 0 auto; }\n");
        html.append(
                "h1 { text-align: center; color: #fff; text-shadow: 2px 2px 4px rgba(0,0,0,0.3); margin-bottom: 30px; }\n");
        html.append(
                "h2 { color: #2c3e50; margin: 0 0 20px 0; padding-bottom: 10px; border-bottom: 3px solid #667eea; }\n");
        html.append(
                ".card { background: #fff; border-radius: 15px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); padding: 25px; margin-bottom: 30px; }\n");
        html.append("table { width: 100%; border-collapse: collapse; margin-top: 10px; }\n");
        html.append(
                "th { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: #fff; padding: 12px 8px; text-align: right; font-weight: 500; }\n");
        html.append("th:first-child { text-align: center; border-radius: 8px 0 0 0; }\n");
        html.append("th:last-child { border-radius: 0 8px 0 0; }\n");
        html.append("td { padding: 10px 8px; text-align: right; border-bottom: 1px solid #eee; }\n");
        html.append("td:first-child { text-align: center; font-weight: 500; color: #667eea; }\n");
        html.append("tr:hover { background: #f8f9ff; }\n");
        html.append("tr:last-child td:first-child { border-radius: 0 0 0 8px; }\n");
        html.append("tr:last-child td:last-child { border-radius: 0 0 8px 0; }\n");
        html.append(".summary-grid { display: grid; grid-template-columns: repeat(5, 1fr); gap: 20px; }\n");
        html.append(
                ".summary-item { background: linear-gradient(135deg, #f5f7fa 0%, #e4e8eb 100%); padding: 20px; border-radius: 10px; text-align: center; }\n");
        html.append(
                ".summary-item.highlight { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: #fff; }\n");
        html.append(".summary-label { font-size: 14px; color: #666; margin-bottom: 8px; }\n");
        html.append(".summary-item.highlight .summary-label { color: rgba(255,255,255,0.8); }\n");
        html.append(".summary-value { font-size: 24px; font-weight: bold; color: #2c3e50; }\n");
        html.append(".summary-item.highlight .summary-value { color: #fff; }\n");
        html.append(
                ".bonus-section { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); padding: 20px; border-radius: 10px; color: #fff; margin-bottom: 20px; }\n");
        html.append(
                ".bonus-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 15px; text-align: center; }\n");
        html.append(".bonus-label { font-size: 12px; opacity: 0.9; }\n");
        html.append(".bonus-value { font-size: 18px; font-weight: bold; margin-top: 5px; }\n");
        html.append("</style>\n</head>\n<body>\n");
        html.append("<div class=\"container\">\n");
        html.append("<h1>年度工资报告</h1>\n");

        // 月度明细表
        html.append("<div class=\"card\">\n");
        html.append("<h2>月度明细</h2>\n");
        html.append("<table>\n<thead>\n<tr>\n");
        html.append("<th>月份</th><th>税前工资</th><th>五险(个人)</th><th>公积金(个人)</th>");
        html.append("<th>五险(企业)</th><th>公积金(企业)</th><th>企业成本</th><th>当月个税</th><th>个人到手</th>\n");
        html.append("</tr>\n</thead>\n<tbody>\n");

        for (int i = 1; i <= 12; i++) {
            html.append("<tr>\n");
            html.append("<td>").append(MONTH_NAMES[i - 1]).append("</td>");
            if (salary.hasMonthData(i)) {
                html.append("<td>").append(df.format(salary.getGrossSalary(i))).append("</td>");
                html.append("<td>").append(df.format(salary.getPersonalSocialInsurance(i))).append("</td>");
                html.append("<td>").append(df.format(salary.getPersonalGjj(i))).append("</td>");
                html.append("<td>").append(df.format(salary.getCompanySocialInsurance(i))).append("</td>");
                html.append("<td>").append(df.format(salary.getCompanyGjj(i))).append("</td>");
                html.append("<td>").append(df.format(salary.getCompanyCost(i))).append("</td>");
                html.append("<td>").append(df.format(salary.getMonthlyTax(i))).append("</td>");
                html.append("<td>").append(df.format(salary.getNetSalary(i))).append("</td>");
            } else {
                html.append("<td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>");
            }
            html.append("\n</tr>\n");
        }
        html.append("</tbody>\n</table>\n</div>\n");

        // 年度汇总
        html.append("<div class=\"card\">\n");
        html.append("<h2>年度汇总</h2>\n");

        // 年终奖部分
        if (salary.getBonusMonths() > 0) {
            html.append("<div class=\"bonus-section\">\n");
            html.append("<div class=\"bonus-grid\">\n");
            html.append("<div><div class=\"bonus-label\">年终奖（").append(String.format("%.1f", salary.getBonusMonths()))
                    .append("个月）</div>");
            html.append("<div class=\"bonus-value\">").append(df.format(salary.getBonusAmount()))
                    .append(" 元</div></div>\n");
            html.append("<div><div class=\"bonus-label\">年终奖个税</div>");
            html.append("<div class=\"bonus-value\">").append(df.format(salary.getBonusTax()))
                    .append(" 元</div></div>\n");
            html.append("<div><div class=\"bonus-label\">年终奖到手</div>");
            html.append("<div class=\"bonus-value\">").append(df.format(salary.getBonusNetAmount()))
                    .append(" 元</div></div>\n");
            html.append("</div>\n</div>\n");
        }

        // 汇总数据
        html.append("<div class=\"summary-grid\">\n");
        html.append("<div class=\"summary-item\"><div class=\"summary-label\">企业用人成本</div>");
        html.append("<div class=\"summary-value\">").append(df.format(salary.getAnnualCompanyCost()))
                .append(" 元</div></div>\n");
        html.append("<div class=\"summary-item\"><div class=\"summary-label\">工资个税</div>");
        html.append("<div class=\"summary-value\">").append(df.format(salary.getAnnualTax()))
                .append(" 元</div></div>\n");
        html.append("<div class=\"summary-item\"><div class=\"summary-label\">个税总额</div>");
        html.append("<div class=\"summary-value\">").append(df.format(salary.getAnnualTotalTax()))
                .append(" 元</div></div>\n");
        html.append("<div class=\"summary-item\"><div class=\"summary-label\">公积金总额</div>");
        html.append("<div class=\"summary-value\">").append(df.format(salary.getAnnualGjj()))
                .append(" 元</div></div>\n");
        html.append("<div class=\"summary-item highlight\"><div class=\"summary-label\">个人到手总额</div>");
        html.append("<div class=\"summary-value\">").append(df.format(salary.getAnnualNetSalary()))
                .append(" 元</div></div>\n");
        html.append("</div>\n</div>\n");

        html.append("</div>\n</body>\n</html>");

        // 写入文件
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(html.toString());
        }
        System.out.println("HTML报告已生成：" + filePath);
    }

    /** 生成HTML报告（默认文件名） */
    public static void generateHtmlReport(Salary salary) throws IOException {
        generateHtmlReport(salary, "salary_report.html");
    }

}
