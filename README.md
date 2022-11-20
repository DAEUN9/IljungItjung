## 목차
1. [서비스 소개](#1-서비스-소개)
2. [주요 기능](#2-주요-기능)
3. [기술 스택](#3-기술-스택)
4. [시스템 구성도](#4-시스템-구성도)
5. [실행 방법](#5-실행-방법)
6. [팀원 소개](#6-팀원-소개)


# 1. 서비스 소개
**일정있정**은 개인과 소상공인을 위한 웹 스케줄링 서비스 입니다.  
네이버, 카카오 미용실과 같은 예약 시스템은 등록된 업체만 이용 가능하며 예약이나 약속을 잡을 때 전화나 SNS를 이용한 커뮤니케이션에 의존적이라는 문제점에 착안하여 쉽고 편하게 이용할 수 있는 웹 서비스를 개발하였습니다.  
‘매우 가벼운’ 일정 예약 툴을 찾고 있는 프로젝트 팀이나 일정 조율, 관리가 중요한 직업이지만 이를 위한 복잡한 절차를 거치기 힘든 소상공인들이 사용할 수 있습니다.

🔗 [노션 바로가기](https://fierce-alpaca-126.notion.site/3329ecd7f4c54f13979f2edce36bd810)

# 2. 주요 기능
### ✅ 일정 예약 기능
고객은 예약 시간, 요청 사항으로 일정을 예약할 수 있습니다.  
고객이 업체의 일정을 확인한 후 빈 시간대에 요청 사항을 입력하면 업체 측에서는 확인 후 수락 및 거절 시 고객에게 메시지를 전송할 수 있습니다.

### ✅ 일정 알림 기능

매일 오전 10시 오늘 일정 알림을 고객에게 전송합니다.

고객이 예약을 신청하거나 취소하면 업체에게 알림 문자를 전송해주고, 업체가 예약을 승인하거나 취소하면 고객에게 알림 문자를 전송해줍니다.


### ✅ 일정 조회 기능
고객은 업체의 일정, 카테고리를 확인할 수 있으며 빈 시간대에 일정 예약을 요청할 수 있습니다.

### ✅ 일정 관리 기능
업체는 카테고리 등록, 수정, 삭제를 통해 카테고리를 관리할 수 있습니다.
업체는 일정 차단 기능을 통해 해당 시간대에 예약이 불가하도록 차단할 수 있습니다.

# 3. 기술 스택
## backend
### local
|Intellij|JDK 11|Spring Boot|Docker|
|:---:|:---:|:---:|:---:|
|<img src="/uploads/019e531cf0901d80f61a422e69ad1b66/image.png" height="70px" />|<img src="/uploads/617a7ac7c8136954aa81358c612cc63e/image.png" height="70px" />|<img src="/uploads/4499a24446e9743ee09ae710704e3c7f/image.png" height="70px" />|<img src="/uploads/3b02b4efda490eeb0d73b6e71cbc3dae/image.png" height="70px" />|
### server (ec2)
|Docker|Jenkins|Nginx|MariaDB|Redis|
|:---:|:---:|:---:|:---:|:---:|
|<img src="/uploads/44d35bc3c1899c266457a5241adb1a1f/image.png" height="70px" />|<img src="/uploads/9d1cf873b8395f1607a81fc603326596/image.png" height="70px" />|<img src="/uploads/f184631071dfd353885661310dfbc406/image.png" height="70px" />|<img src="/uploads/9454b8b1d3aee00aa5f3838280086c9d/image.png" height="70px" />|<img src="/uploads/c654ef5538849e5c6dcf692bf0fb7979/2.png" height="70px" />|

## frontend
|HTML|CSS|JavaScript|TypeScript|
|:---:|:---:|:---:|:---:|
|<img src="/uploads/6793b173ce186a19ae856cf8039dceb6/image.png" height="70px" />|<img src="/uploads/66f51d79a3c95cc4f6ec938444491e37/image.png" height="70px" />|<img src="/uploads/b5bf77d10412a63533ab5c6f513d596c/image.png" height="70px" />|<img src="/uploads/3dc22e271d971a5bf8f31dfa41bc9241/3.png" height="70px" />|

|React|Redux|Sass|Mui|
|:---:|:---:|:---:|:---:|
|<img src="/uploads/92970f5563834f04e171b1a80d8c0b4e/image.png" height="70px" />|<img src="/uploads/b7a47272d61662e3bd8ec969157f9318/image.png" height="70px" />|<img src="/uploads/65a4d03024f914b7547816ec66785a8f/image.png" height="70px" />|<img src="/uploads/f4197a6763483e6552cf82e27fd40bab/image.png" height="70px" />|

# 4. 시스템 구성도

![제목_없는_다이어그램.drawio](/uploads/ebef644f5d0403a88d5f6a6374dec53f/제목_없는_다이어그램.drawio.png)

# 5. 실행 방법
## backend
### CI/CD  
gitlab(back branch) push or merge -> jenkins webhook -> auto build  

### SSH
```
ssh -i K7D106T.pem ubuntu@k7d106.p.ssafy.io
```

### docker logs
![image](/uploads/cea753e37746b5ce94b991333c57c16a/image.png)
```
docker logs --tail 300 iljungitjung_server_green
```

## frontend
1. 레포지토리를 clone 받는다.
```bash
git clone https://lab.ssafy.com/s07-final/S07P31D106.git
```
2. `front` 폴더에서 `package.json`에 정의된 모듈을 설치한다.
```shell
npm install
```
3. 프로그램을 실행시킨다.
```shell
npm start
```


# 6. 팀원 소개
|조인후|김다은|김주영|이기종|이상민|이재영|
|:---:|:---:|:---:|:---:|:---:|:---:|
|front(팀장)|back|front|back|back|front|
