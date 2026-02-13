#!/usr/bin/env python3
"""
调试脚本：专门分析 Arrays & Hashing.md
"""

import re

def debug_analyze_file():
    filepath = '/Users/ganten/workspace/github/tethys/documents/leetcode/01. Arrays & Hashing.md'
    
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    problem_pattern = r'^#{1,3}\s*(\d+)\.\s*\[([^\]]+)\]\([^)]+\)'
    lines = content.split('\n')
    
    print("🔍 开始调试 Arrays & Hashing.md...")
    print(f"文件总行数: {len(lines)}")
    print("="*60)
    
    found_problems = 0
    
    i = 0
    while i < len(lines):
        line = lines[i].strip()
        match = re.match(problem_pattern, line)
        
        if match:
            found_problems += 1
            problem_num = int(match.group(1))
            problem_name = match.group(2)
            
            print(f"\n📋 找到题目 #{found_problems}: {problem_num}. {problem_name}")
            print(f"在第 {i+1} 行: {line}")
            
            # 查找代码块
            j = i + 1
            code_found = False
            has_meaningful_code = False
            
            print(f"🔍 搜索代码块...")
            search_count = 0
            while j < len(lines) and j < i + 50:
                search_line = lines[j].strip()
                search_count += 1
                
                if search_line.startswith('```java'):
                    print(f"  ✅ 找到Java代码块在第 {j+1} 行")
                    
                    # 收集代码内容
                    k = j + 1
                    code_lines = []
                    while k < len(lines) and not lines[k].strip().startswith('```'):
                        code_lines.append(lines[k])
                        k += 1
                    
                    print(f"  📝 代码块长度: {len(code_lines)} 行")
                    
                    # 分析代码内容
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
                    
                    print(f"  💡 有意义的代码行数: {len(meaningful_lines)}")
                    
                    if len(meaningful_lines) >= 3:
                        code_text = '\n'.join(meaningful_lines).lower()
                        keywords = ['class ', 'public ', 'private ', 'protected ',
                                   'return ', 'if (', 'if(', 'for (', 'for(', 
                                   'while (', 'while(', 'int ', 'string ', 
                                   'boolean ', 'void ', 'new ', 'system.out',
                                   'hashset', 'arraylist', 'linkedlist', 'hashmap',
                                   'math.', 'arrays.', '.length', '.add(', '.get(',
                                   'solution', 'leetcode', 'nums', 'target']
                        
                        found_keywords = [kw for kw in keywords if kw in code_text]
                        print(f"  🔑 找到的关键字: {found_keywords}")
                        
                        has_meaningful_code = len(found_keywords) > 0
                    
                    code_found = True
                    break
                elif search_line.startswith('#'):
                    print(f"  ⏹️  遇到下一个标题在第 {j+1} 行，停止搜索")
                    break
                    
                j += 1
            
            if not code_found:
                print(f"  ❌ 未找到代码块 (搜索了 {search_count} 行)")
            
            completion_status = "✅ 已完成" if has_meaningful_code else "❌ 未完成"
            print(f"  🏁 结论: {completion_status}")
            
            if found_problems >= 5:  # 只看前5个题目
                print(f"\n... (只显示前5个题目的详细信息)")
                break
        
        i += 1
    
    print(f"\n📊 总结:")
    print(f"总共找到 {found_problems} 个题目")

if __name__ == "__main__":
    debug_analyze_file()