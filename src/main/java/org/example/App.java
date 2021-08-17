package org.example;

import lombok.Getter;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {

    static class DelayObject<T> implements Delayed {

        @Getter
        private final T data;
        private final long sleep;

        public DelayObject(T data, long sleepMills) {
            this.data = data;
            this.sleep = System.currentTimeMillis() + sleepMills;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long m = unit.convert(sleep - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            System.out.println(Thread.currentThread().getName() + " : " + m);
            return m;
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(sleep, ((DelayObject<?>) o).sleep);
        }
    }

    // DealyQueue
    // take()호출 시 내부 우선순위 큐를 getDelay()만큼 쉬면서 계속 폴링한다
    // getDealy()가 0보다 작거나 같으면 반환한다.
    // scheduledexecutor는 내부에 DelayQueue와 비슷한 큐를 사용한다.
    public static void main( String[] args ) throws InterruptedException {
        DelayQueue<DelayObject<String>> q = new DelayQueue<>();
        for (int i = 0; i < 100; i++) {
            q.put(new DelayObject<>(String.valueOf(i), 1000));
        }

        while (!q.isEmpty()) {
            System.out.println(q.take().getData());
            System.out.println("!");
        }

    }
}
