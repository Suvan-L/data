@echo ************************** �������� vpn **************************
::     ���� vpn ����   |   �ʺ�   |    ����
rasdial "TEST" vpnname vpnpassword


cd C:\project
@echo ************************** ���ڸ��� project_1 ���� **************************
svn update project_1

@echo ************************** ���ڸ��� project_2 ���� **************************
svn update project_2


@echo ************************** ���ڶϿ� vpn **************************
:: �Ͽ� vpn
rasdial "TEST" /DISCONNECT

pause