# az spring app create \
#    --resource-group dev \
#    --service respiro  \
#    --name teoriproven \
#    --assign-endpoint true


az spring app deploy \
--resource-group dev \
--service respiro \
--name teoriproven \
--subscription 100b4a26-f05e-4040-bcb6-e7534c5e8e02 \
--artifact-path /Users/gregtaube/IdeaProjects/hrApplication/out/artifacts/hrapplication_main_jar/hrapplication.main.jar