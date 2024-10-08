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
const int ident_1 = 0;
const int ident_2 = 1 ,ident_3 =2;
const char ident_8 = 'a';
int ident_9;
int ident_10,ident_11;
int ident_14=1;
int ident_16=1,ident_17='a';
void func_1()
{
    
}
int func_2(int a)
{
    a=2;
    int b=1;
    return 1;
}
char func_3(int b)
{
    return 'a';
}
void func_4(int b,int c)
{
    return;
}
int func_5()
{
    return 1;
}
int main()
{
    int a;
    a = getint();
    char c;
    c = getchar();
    if('a')
    {
        a=1;
    }
    else
    {
        a=1;
    }
    if(1)
    {
        a=1;
    }
    if(a)
    {
        a=2;
    }
    if((1+1))
    {
        a=2;
    }
    a=0;
    if(!a)
    {
        a=2;
    }
    if(a*1)
    {
        a=2;
    }
    if(a/1)
    {
        a=3;
    }
    if(a%2)
    {
        a=3;
    }
    if(a+1)
    {
        a=3;
    }
    if(a-1)
    {
        a=7;
    }
    if(a>6)
    {
        a=7;
    }
    if(a<8)
    {
        a=7;
    }
    if(a>=6)
    {
        a=7;
    }
    if(a<=8)
    {
        a=7;
    }
    if(a==7)
    {
        a=7;
    }
    if(a!=8)
    {
        a=7;
    }
    for(a=7;a<=10;a=a+1)
    {
        a=a+1;
        continue;
    }
    for(;a<=11;a=a+1)
    {
        break;
    }
    for(a=12;;a=a+1)
    {
        break;
    }
    for(a=12;a<=12;)
    {
        break;
    }
    for(a=12;;)
    {
        break;
    }
    for(;a<=12;)
    {
        break;
    }
    for(;;a=a+1)
    {
        break;
    }
    for(;;)
    {
        break;
    }
    a=10;
    a = a+1;
    a = a-1;
    a = a * 10;
    a = a/10;
    func_1();
    a=func_5();
    func_2(a);
    func_4(a,1);
    {
        a=10;
        a=+10;
        a=-10;
        ;
    }
    {

    }
    printf("22373212\n");
    printf("%d\n", a);
    printf("%d\n", a);
    printf("%d\n", a);
    printf("%d\n", a);
    printf("%d\n", a);
    printf("%d\n", a);
    printf("%d\n", a);
    printf("%d\n", a);
    printf("%d\n", a);
    return 0;
}


