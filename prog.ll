;LLVM version 3.8.0 (http://llvm.org/)
;program teste
declare i32 @printf(i8*, ...) nounwind
@str_print_int = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@str_print_double = private unnamed_addr constant [7 x i8] c"%.2lf\0A\00", align 1
define i32 @main() nounwind {
%a = alloca i32
store i32 0, i32* %a
%b = alloca i32
store i32 0, i32* %b
store i32 4, i32* %b
%1 = sub i32 0, 2
%2 = sub i32 5, %1
%3 = load i32, i32* %b
%4 = add i32 %3, 1
store i32 %4, i32* %b
%5 = add i32 %2, %3
store i32 %5, i32* %a
%6 = load i32, i32* %a
%7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds([4 x i8], [4 x i8]* @str_print_int, i32 0, i32 0), i32 %6)
ret i32 0
}
