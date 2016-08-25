#!/bin/bash

set -e

function run_test()
{
    ./mvn.sh -f $1/pom.xml -P amp-to-war integration-test -Dmaven.tomcat.port=$2 &
    while ! curl http://127.0.0.1:$2/$3 -o /dev/null; do
        sleep 1;
    done
    test $( curl -s -o /dev/null -w "%{http_code}" http://127.0.0.1:$2/$3 ) -lt 400;
}

if [[ "$1" = "repo" ]]; then
    run_test repo 8080 alfresco;
elif [[ "$1" = "share" ]]; then
    run_test share 8081 share;
else
    false;
fi
