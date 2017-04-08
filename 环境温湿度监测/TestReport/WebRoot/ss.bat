@echo off 
rem 关闭自动输出
:begin
rem 接收输入
set input=
set /p input=mis.war:
jar -cvf  %input%  *
pause  打包完成