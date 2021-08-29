@echo off
rem запуск приложений на машине 192.168.0.101

cd C:\Users\yuriy\work\apps\elasticsearch-7.13.2
start "Elastic" bin\elasticsearch.bat
pause
cd C:\Users\yuriy\work\apps\kibana-7.13.2
start "Kibana" bin\kibana.bat
