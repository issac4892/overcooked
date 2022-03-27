# Paper Development Sample Plugin

## TODO

- [ ] 남은 시간 타이머 구현
- [ ] 조리 시간 확인 구현
- [ ] 리스폰 시간 확인 구현
- [ ] 전체 점수 띄워주기 구현
- [ ] 플레이어별 점수 생각해보기
- [ ] 음식 갖다내는거 구현
- [ ] 쓰레기통 구현
- [ ] 맵마다 각종 장치 파악하는거 구현

## 음식 받아오기
- 아이템액자 있는 상자 클릭
- 이벤트 취소
- 아이템 지급

---

- 한손에는 하나의 아이템만
- 2번은 배리어, 3번부터는 레시피 목록
- 레시피는 최대 7개
- 레시피: 들어온 주문

## Environment

- [Paper 1.18.2](https://papermc.io/downloads) dependencies
- [Tap](https://github.com/monun/tap) dependencies
- [Kommand](https://github.com/monun/kommand/) dependencies
- [Server Script](https://github.com/monun/server-script) / Using [aroxu's GoLang version](https://github.com/aroxu/server-script/)

### This Project Requires:

- [JDK 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) (Corretto JDK is Recommended.)
- libarchive-tools package (on Linux|Shell, for [./server-script.sh](./server-script.sh))
