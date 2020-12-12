#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용중 | real2 휴식

function find_idle_profile() {
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile) # 현재 ngnix가 보고 있는 스프링 부트 정상 작동 여부 확인
    if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면 에러
    then
      CURRNET_PROFILE=real2
    else
      CURRNET_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRNET_PROFILE} == real1 ]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi

    echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1]
  then
    echo "8081"
  else
    echo "8082"
  fi

}