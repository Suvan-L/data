@echo ************************** 正在连接 vpn **************************
::     本地 vpn 名称   |   帐号   |    密码
rasdial "TEST" vpnname vpnpassword


cd C:\project
@echo ************************** 正在更新 project_1 代码 **************************
svn update project_1

@echo ************************** 正在更新 project_2 代码 **************************
svn update project_2


@echo ************************** 正在断开 vpn **************************
:: 断开 vpn
rasdial "TEST" /DISCONNECT

pause