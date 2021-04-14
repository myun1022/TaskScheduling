import java.util.*;

public class TaskScheduling {

    static int[][] task;
    static int n;           //작업이 완전히 다 끝나는 시간
    static int t;           //작업의 개수
    static int[] m;         //기계

    public static void randomTask(int n, int t) {

        Random r = new Random();

        task = new int[n][n];
        m = new int[n];


        for (int i = 1; i < t+1; i++) {
            int a = r.nextInt(n - 1);                               //a는 0이상 n미만
            int b = r.nextInt(n - a - 1) + a + 1;                   //b는 a초과 n이하

            if (task[a][b] != 0) {
                i--;

            }
            else{
                task[a][b] = i;                                                //i번째 작업 생성

                System.out.printf("%d 번째 작업 : [%d, %d]", i, a, b);
                System.out.println();
            }

        }
    }


    public static void main(String[] args) {

        /*task= new int[n][n];
        m = new int[n];

        task[7][8] = 1;
        task[3][7] = 2;
        task[1][5] = 3;
        task[5][9] = 4;
        task[0][2] = 5;
        task[6][8] = 6;
        task[1][6] = 7;*/

        Scanner sc = new Scanner(System.in);

        System.out.printf("작업의 개수를 입력 : ");
        t = sc.nextInt();

        System.out.printf("작업이 마지막으로 끝나는 시간 입력 : ");
        n = sc.nextInt();

        TaskScheduling.randomTask(n, t);

        int t = -1;  //현재 시간

        for (int i = 0; i < n; i++) {
            t++;                                        //현재 시간을 +1 해줌
            for (int h = 0; h < n; h++) {
                if (m[h] != 0) {                          //작업중인 기계가 있으면
                    m[h] = m[h] - 1;                      //작업 남은 시간 -1 해줌
                }
            }


            for (int j = 0; j < n; j++) {
                if (task[i][j] != 0) {                    //작업이 있으면
                    for (int k = 0; k < n; k++) {             //작업중이지 않은 기계 찾기
                        if (m[k] == 0) {

                            m[k] = j - i;                //작업시간 만큼 기계에 남은 시간 배정
                            System.out.printf("작업 %d 번이 %d 번째 기계에 배정됨", task[i][j], k);
                            System.out.println();
                            task[i][j] = 0;             //실행한 작업 지우기
                            break;
                        }
                    }
                }
            }
        }
    }
}