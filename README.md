# DealyQueue
* `take()`호출 시 내부 우선순위 큐를 `getDelay()`만큼 쉬면서 계속 폴링한다
* `getDealy()`가 0보다 작거나 같으면 반환한다.
* `scheduledexecutor`는 내부에 `DelayQueue`와 비슷한 큐를 사용한다.