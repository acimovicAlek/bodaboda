FROM microsoft/aspnetcore:2.1
 
WORKDIR /app
COPY bin/Debug/netcoreapp2.1 .
 
ENV ASPNETCORE_URLS http://+:5000
EXPOSE 5000
 
ENTRYPOINT ["dotnet", "BodaBodaServer.dll"]
