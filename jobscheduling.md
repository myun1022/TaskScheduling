```java
import java.util.Scanner;

class job {       //작업의 시작시간 s와 끝시간 f를 저장
    int s, f;
    public job(int s, int f) {
        this.s = s;
        this.f = f;
    }
}

public class Jobscheduling {
    public static job[] sort(job[] t, int n) {      //작업을 시작시간의 오름차순으로 정렬
        job[] L;
        L = new job[n];

        boolean[] check = new boolean[n];

        for(int i=0; i<n; i++) {
            int min = Integer.MAX_VALUE;
            for(int j=0; j<n; j++) {
                if(!check[j])
                    min = Math.min(min, t[j].s);
            }
            for(int j=0; j<n; j++)
                if(min == t[j].s && !check[j]) {
                    L[i] = new job(t[j].s, t[j].f);
                    check[j] = true;
                    break;
                }
        }
        return L;
    }

    public static void scheduling(job[] t, int n) {     //jobscheduling
        job[] L = sort(t, n);       //시작시간의 오름차순으로 정렬된 배열 L
        job[][] machine = new job[n][n];      //수행시간이 중복되지 않게 작업을 배정할 2차원 배열 machine

        boolean[] check = new boolean[n];      //작업 배정 여부를 확인할 배열 check
        for(int i=0; i<n; i++) {
            int count = 0;     //작업의 순서
            int finish = 0;     //가장 마지막 작업의 끝시간
            for(int j=0; j<n; j++)
                if(L[j].s >= finish && !check[j]) {       //아직 배정되지 않은 작업의 시작시간이 기계에 배정된 작업의 끝시간과 같거나 그 이후일 경우
                    machine[i][count] = L[j];           //작업 배정
                    finish = machine[i][count].f;        //가장 마지막 작업의 끝시간 갱신
                    check[j] = true;
                    count++;
                }
        }

        for(int i=0; i<n; i++){       //기계 별로 배정된 작업을 순서대로 출력
            for(int j=0; j<n; j++) {
                if(machine[i][j] == null)
                    break;
                System.out.print("[" + machine[i][j].s + ", " + machine[i][j].f + "]");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        n = scanner.nextInt();      //작업의 개수 입력

        job[] t = new job[n];
        for(int i=0; i<n; i++) {
            int s, f;
            s = scanner.nextInt();      //작업의 시작시간 입력
            f = scanner.nextInt();      //작업의 끝시간 입력
            t[i] = new job(s,f);
        }

        scheduling(t, n);

        scanner.close();
    }
}
```