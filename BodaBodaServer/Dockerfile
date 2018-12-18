FROM microsoft/dotnet:2.1-aspnetcore-runtime
 
WORKDIR /app
COPY published .
 
ENV ASPNETCORE_URLS http://+:5000
EXPOSE 5000
 
ENTRYPOINT ["dotnet", "BodaBodaServer.dll"]
