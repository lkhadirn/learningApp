rsync --rsync-path="sudo rsync" teoriproven.conf ubuntu@oslo1:/etc/nginx/conf.d/
ssh ubuntu@oslo1 "sudo systemctl restart nginx"