#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
LeetCode 进度统计脚本
扫描 documents/leetcode/ 目录下的所有题目，统计完成情况
"""

import os
import re
from pathlib import Path
from collections import defaultdict

def analyze_leetcode_file(filepath):
    """分析单个 leetcode 文件的完成情况"""
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # 匹配题目模式：# 或 ## 或 ### 后面跟数字和题目名
    # 支持多种格式：
    # # 1. [Two Sum](...)
    # ## 215. [Kth Largest](...)
    # ### 70. [Climbing Stairs](...)
    problem_pattern = r'^#{1,3}\s*(\d+)\.\s*\[([^\]]+)\]\(([^)]+)\)'
    
    problems = []
    lines = content.split('\n')
    
    i = 0
    while i < len(lines):
        line = lines[i].strip()
        match = re.match(problem_pattern, line)
        
        if match:
            problem_num = int(match.group(1))
            problem_name = match.group(2)
            problem_url = match.group(3)
            
            # 查找后续的代码块
            j = i + 1
            has_code = False
            code_content = ""
            
            # 找到下一个代码块，允许跳过一些介绍性内容
            while j < len(lines) and j < i + 50:  # 限制搜索范围，避免过度搜索
                if lines[j].strip().startswith('```java'):
                    # 找到代码块的结束
                    k = j + 1
                    code_lines = []
                    while k < len(lines) and not lines[k].strip().startswith('```'):
                        code_lines.append(lines[k])
                        k += 1
                    
                    code_content = '\n'.join(code_lines).strip()
                    # 判断代码是否有实质内容
                    # 过滤掉注释、空行、import语句等，检查是否有实际代码逻辑
                    meaningful_lines = []
                    for line in code_lines:
                        stripped = line.strip()
                        if (stripped and 
                            not stripped.startswith('//') and 
                            not stripped.startswith('/*') and 
                            not stripped.startswith('*') and
                            not stripped.startswith('import') and
                            not stripped.startswith('package') and
                            stripped not in ['{', '}', '*/']):
                            meaningful_lines.append(stripped)
                    
                    # 如果有类声明、方法声明或任何逻辑代码，则认为是有效的
                    code_text = '\n'.join(meaningful_lines).lower()
                    has_code = (len(meaningful_lines) >= 3 and  # 至少3行有意义的代码
                               any(keyword in code_text 
                                   for keyword in ['class ', 'public ', 'private ', 'protected ',
                                                 'return ', 'if (', 'if(', 'for (', 'for(', 
                                                 'while (', 'while(', 'int ', 'string ', 
                                                 'boolean ', 'void ', 'new ', 'system.out',
                                                 'hashset', 'arraylist', 'linkedlist', 'hashmap',
                                                 'math.', 'arrays.', '.length', '.add(', '.get(',
                                                 'solution', 'leetcode', 'nums', 'target']))
                    break
                elif lines[j].strip().startswith('## '):
                    # 只在遇到同级或更高级标题时停止（## 或 #）
                    # 跳过子标题（###）继续搜索
                    break
                j += 1
            
            problems.append({
                'number': problem_num,
                'name': problem_name,
                'url': problem_url,
                'completed': has_code,
                'code_length': len(code_content) if code_content else 0
            })
        
        i += 1
    
    return problems

def generate_progress_stats():
    """生成所有文件的进度统计"""
    leetcode_dir = Path('/Users/ganten/workspace/github/tethys/documents/leetcode')
    
    if not leetcode_dir.exists():
        print("LeetCode 目录不存在")
        return
    
    all_stats = {}
    total_completed = 0
    total_problems = 0
    
    # 文件名到标题的映射
    file_titles = {
        '01. Arrays & Hashing.md': 'Arrays & Hashing',
        '02. Two Pointers.md': 'Two Pointers',
        '03. Sliding Window.md': 'Sliding Window',
        '04. Binary Search.md': 'Binary Search',
        '05. Stack.md': 'Stack',
        '06. Linked List.md': 'Linked List',
        '07. Trees.md': 'Trees',
        '08. Graphs.md': 'Graphs',
        '09. Heaps & Priority Queue.md': 'Heaps & Priority Queue',
        '10. Backtracking.md': 'Backtracking',
        '11. Dynamic Programming.md': 'Dynamic Programming',
        '12. Greedy.md': 'Greedy',
        '13. Intervals.md': 'Intervals',
        '14. Data Structure Design.md': 'Data Structure Design',
        '15. String Algorithms.md': 'String Algorithms'
    }
    
    for md_file in sorted(leetcode_dir.glob('*.md')):
        filename = md_file.name
        if filename in file_titles:
            print(f"分析文件: {filename}")
            
            problems = analyze_leetcode_file(md_file)
            completed = sum(1 for p in problems if p['completed'])
            total = len(problems)
            
            completion_rate = (completed / total * 100) if total > 0 else 0
            
            all_stats[filename] = {
                'title': file_titles[filename],
                'completed': completed,
                'total': total,
                'completion_rate': completion_rate,
                'problems': problems
            }
            
            total_completed += completed
            total_problems += total
            
            print(f"  - 完成: {completed}/{total} ({completion_rate:.1f}%)")
    
    # 打印总体统计
    overall_rate = (total_completed / total_problems * 100) if total_problems > 0 else 0
    print(f"\n📊 总体进度: {total_completed}/{total_problems} ({overall_rate:.1f}%)")
    
    return all_stats, total_completed, total_problems

def generate_readme_content(stats, total_completed, total_problems):
    """生成更新后的 README 内容"""
    overall_rate = (total_completed / total_problems * 100) if total_problems > 0 else 0
    
    readme_content = f"""# 🔥 LeetCode 刷题进度 & 题目清单

> 📊 **总进度**: {total_completed}/{total_problems} 题目已完成 ({overall_rate:.1f}%)  
> 🎯 **目标**: 覆盖所有高频面试题目  
> 📅 **最近更新**: 2026年2月13日 (自动生成)

---

"""

    # 为每个模块生成内容
    for i, (filename, data) in enumerate(stats.items(), 1):
        title = data['title']
        completed = data['completed']
        total = data['total']
        rate = data['completion_rate']
        
        # 根据完成度选择图标和颜色
        if rate >= 80:
            icon = '🟢'
            status = '✅ 完成度高'
        elif rate >= 50:
            icon = '🟡'
            status = '🔄 进行中'
        elif rate >= 20:
            icon = '🟠'
            status = '🔄 部分完成'
        else:
            icon = '🔴'
            status = '❌ 待开始'
        
        readme_content += f"""# {icon} {i}. {title} ({status}: {rate:.0f}%)

**进度**: {completed}/{total} 题目已完成

"""
        
        # 列出已完成和未完成的题目
        completed_problems = [p for p in data['problems'] if p['completed']]
        incomplete_problems = [p for p in data['problems'] if not p['completed']]
        
        if completed_problems:
            readme_content += "**✅ 已完成题目**:\n"
            for problem in completed_problems[:10]:  # 只显示前10个
                readme_content += f"{problem['number']}. [{problem['name']}]({problem['url']}) ✅\n"
            
            if len(completed_problems) > 10:
                readme_content += f"... 还有 {len(completed_problems) - 10} 个已完成题目\n"
            readme_content += "\n"
        
        if incomplete_problems:
            readme_content += "**❌ 待完成题目**:\n"
            for problem in incomplete_problems[:8]:  # 只显示前8个未完成的
                readme_content += f"{problem['number']}. [{problem['name']}]({problem['url']}) ❌\n"
            
            if len(incomplete_problems) > 8:
                readme_content += f"... 还有 {len(incomplete_problems) - 8} 个待完成题目\n"
        
        readme_content += "\n---\n\n"
    
    # 添加统计图表
    readme_content += """# 📈 完成度统计

## 各模块完成情况

```
"""
    
    for filename, data in stats.items():
        title = data['title']
        rate = data['completion_rate']
        completed = data['completed']
        total = data['total']
        
        # 生成进度条
        bar_length = 20
        filled_length = int(rate / 100 * bar_length)
        bar = '█' * filled_length + '░' * (bar_length - filled_length)
        
        readme_content += f"{title:<25} │{bar}│ {rate:5.1f}% ({completed:2d}/{total:2d})\n"
    
    readme_content += """```

## 🏆 成就系统

"""
    
    # 根据完成度添加成就
    achievements = []
    if overall_rate >= 90:
        achievements.append("🏆 **大师级** - 完成度超过90%!")
    elif overall_rate >= 70:
        achievements.append("🥇 **专家级** - 完成度超过70%!")
    elif overall_rate >= 50:
        achievements.append("🥈 **熟练级** - 完成度超过50%!")
    elif overall_rate >= 30:
        achievements.append("🥉 **入门级** - 完成度超过30%!")
    
    if total_completed >= 100:
        achievements.append("💯 **百题达成** - 完成超过100题!")
    elif total_completed >= 50:
        achievements.append("🎯 **半百达成** - 完成超过50题!")
    
    # 检查特定模块的成就
    for filename, data in stats.items():
        if data['completion_rate'] == 100:
            achievements.append(f"✨ **{data['title']}模块完全掌握**!")
    
    if achievements:
        for achievement in achievements:
            readme_content += f"- {achievement}\n"
    else:
        readme_content += "- 🌱 **刚刚起步** - 继续加油，成就在等着你!\n"
    
    readme_content += """
---

## 📝 使用说明

1. ✅ = 已完成代码实现
2. ❌ = 待完成
3. 🔄 = 正在进行中

**注意**: 此统计由脚本自动生成，反映当前代码完成情况。
"""
    
    return readme_content

if __name__ == "__main__":
    print("🔍 开始分析 LeetCode 进度...")
    
    try:
        stats, total_completed, total_problems = generate_progress_stats()
        
        print("\n📝 生成 README 内容...")
        new_readme_content = generate_readme_content(stats, total_completed, total_problems)
        
        # 写入新的 README
        readme_path = '/Users/ganten/workspace/github/tethys/readme.md'
        with open(readme_path, 'w', encoding='utf-8') as f:
            f.write(new_readme_content)
        
        print(f"✅ README 已更新! 路径: {readme_path}")
        print(f"📊 总体统计: {total_completed}/{total_problems} ({total_completed/total_problems*100:.1f}%)")
        
    except Exception as e:
        print(f"❌ 错误: {e}")
        import traceback
        traceback.print_exc()