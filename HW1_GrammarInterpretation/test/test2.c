#include <stdio.h>

int getchar(){
    char c;
    scanf("%c", &c);
    return (int)c;
}
int getint(){
    int t;
    scanf("%d", &t);
    while (getchar() != '\n')
        ;
    return t;
}

// 常量声明，包含普通变量、一维数组和二维数组
const int a = 5, b[2] = {1, 2};
const int matrix[2][2] = {{1, 2}, {3, 4}};
const char c = 'A';
char str[6] = "Hello";

// 变量声明，包含普通变量、一维数组和二维数组
int x, y = 10, z[2] = {4, 5};
int mat[2][2] = {{5, 6}, {7, 8}};
char ch = 'B', name[6] = "World";

// 函数定义，有形参的函数
int add(int p1, int p2) {
    int p3;
    p3 = p1 + p2;
    return p3;
}

// 无形参的函数定义
void greet() {
    printf("Hello, World!\n");  // 覆盖 printf 无 Exp 的情况
    return;
}

// 覆盖一元运算符和乘除模表达式
int unary_ops() {
    int a = +5, b = -a, c = !0;  // 包含 +、−、!
    int d;
    d = a *b / 2 % 2; // 覆盖 *, /, %
    return 0;
}

// 覆盖关系表达式、相等性表达式、逻辑与或表达式
int relational_and_logical() {
    int a = 5, b = 3;
    char c = 'A', d = 'B';
    int result=0;
    if(a>b && c=='A'){
        result = result + 1;
    }
    if(a<b || d!='B'){
        result = result + 1;
    }
    return result;
}

// 主函数定义
int main() {
    //首先输出学号
    printf("22373057\n");
    // 覆盖变量声明，普通变量、数组和二维数组的赋值
    int i = 0, j[3] = {1, 2, 3}, matrix2D[2][2] = {{9, 10}, {11, 12}};
    char d = 'D';

    // 覆盖 if-else 语句
    if (i < 5) {
        i = i + 1;
    } else {
        i = i - 1;
    }

    // 覆盖 for 循环，ForStmt 和 Cond 的不同情况
    for (int k = 0; k < 3; k=k+1) {
        printf("k: %d ", k);
    }
    printf("\n");
    for (int m = 0;; m=m+1) {  // Cond 缺省
        if (m > 2) break;
    }

    for (;; i=i+1) {  // ForStmt 和 Cond 全部缺省
        if (i > 6) break;
    }

    // 覆盖二维数组的遍历
    int row;
    int col;
    for (row = 0; row < 2; row=row+1) {
        for (col =0; col < 2; col=col+1) {
            printf("matrix2D[%d][%d] = %d ", row, col, matrix2D[row][col]);
        }
    }
    
    printf("\n");
    // printf("%d %d", row, col);

    // 覆盖 break 和 continue
    int n;
    for (n = 0; n < 10; n = n + 1)
    {
        if (n == 5) break;
        if (n == 2) continue;
        printf("n: %d ", n);
    }
    printf("\n");
    // 覆盖 LVal getint 和 getchar
    i = getint();
    d = getchar();

    // 覆盖 printf，包含字符串和多个表达式
    printf("all done\n");

    return 0;
}





