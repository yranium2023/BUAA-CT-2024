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

const int const_int_var_1 = 0;
const int const_int_var_2 = 0, const_int_var_3 = 1;
const int s1[2] = {1, 2};
const int matrix[2][2] = {{1, 2}, {3, 4}};

const char const_char_var_1 = 'a';

int int_var_1=1;
int int_var_2, int_var_3;
char hello[6] = "hello";

void f1(){

}
void f1_1(){
    return;
}
int f2(int a){
    int b;
    b = 1;
    return b;
}
char f3(){
    return 'a';
}
char f4(char c){
    return c;
}
int f5(){
    return 0;
}

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

int main(){
    int a;
    a = getint();
    char ch;
    ch = getchar();
    int i=0;
    if (i < 5) {
        i = i + 1;
    } else {
        i = i - 1;
    }
    int k = 0;
    for (k = 0; k < 3;k=k+1){

    }
    for (; k < 5;k=k+1){

    }
    int m = 0;
    for (m = 0;; m = m + 1)
    { // Cond 缺省
        if (m > 2) break;
    }
    for (;; i=i+1) {  // ForStmt 和 Cond 全部缺省
        if (i > 6) break;
    }
    int n;
    for (n = 0; n < 10; n = n + 1)
    {
        if (n == 5) break;
        if (n == 2) continue;
    }
    printf("22373057\n");
    printf("1\n");
    printf("2\n");
    printf("3\n");
    printf("4\n");
    printf("5\n");
    printf("6\n");
    printf("7\n");
    printf("8\n");
    printf("all done");
}
