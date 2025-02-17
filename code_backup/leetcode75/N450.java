package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

// TODO: 需要复习
public class N450 {
    /*
                       15
                     /    \
                   7       24
                /     \
              3        10
             /  \     /   \
            2    4   8    12
      0. 如果删除 7

                       15
                     /    \
                   [7]       24
                /     \
              3        10
             /  \     /   \
            2    4   8    12
      1.  最终找到 rightSmallest = 8
      2.    将 7 的左边接到 8 的左边

                       15
                     /    \
                  [7]       24
                 /    \
            [          10        ]
            [         /   \      ]
            [        8    12     ]
            [       /            ]
            [     3              ]
            [    /  \            ]
            [   2    4           ]

       3.  然后将 10 的整个下面，代替 7

                       15
                     /    \
        [          10       24    ]
        [         /   \           ]
        [        8    12          ]
        [       /                 ]
        [     3                   ]
        [    /  \                 ]
        [   2    4                ]
     */
    // 递归实现的步骤 3
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val > key) {
            root.left = this.deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = this.deleteNode(root.right, key);
        } else {
            // delete current node
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode rightSmallest = root.right;
            // 步骤 1
            while (rightSmallest.left != null) {
                rightSmallest = rightSmallest.left;
            }
            // 步骤 2
            rightSmallest.left = root.left;
            return root.right;
        }
        return root;
    }
}
