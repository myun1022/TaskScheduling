//기계당 어떤 작업이 어떤 순서로 배정되었는지 확인할 수 있도록 구현하고 싶었는데 고민해보고 이것저것 시도해도 잘 모르겠어서 정렬한 task 순서대로 어떤 기계에 배정되는지 구현해보았습니다.

public class Task {

    static void XmergeSort(int[][] A, int p, int q) {      //합병정렬을 사용하여 작업의 시작시간 오름차순으로 정렬
        if (p >= q) return;
        int k = (p + q) / 2;

        XmergeSort(A, p, k);
        XmergeSort(A, k + 1, q);
        Xmerge(A, p, k, q);
    }

    static void Xmerge(int[][] A, int p, int k, int q) {     //합병정렬
        int[][] C = new int[q - p + 1][2];
        int i = p, j = k + 1, n = 0;

        while (i <= k && j <= q) {
            C[n][0] = A[i][0] < A[j][0] ? A[i][0] : A[j][0];
            C[n++][1] = A[i][0] < A[j][0] ? A[i++][1] : A[j++][1];
        }
        while (i <= k) {
            C[n][0] = A[i][0];
            C[n++][1] = A[i++][1];
        }
        while (j <= q) {
            C[n][0] = A[j][0];
            C[n++][1] = A[j++][1];
        }
        for (int a = p, b = 0; a <= q; a++) {
            A[a][0] = C[b][0];
            A[a][1] = C[b++][1];
        }
    }

    static void JobScheduling(int[][] task) {
        int m=0;       //사용된 기계의 수-1
        int[] machine= new int[task.length];    //사용되는 기계
        int[] sch = new int[task.length];  //task가 배정되었는지 확인(0이면 배정X, 1이면 배정O)

        for(int i=0; i< task.length;i++)    //배열 sch를 0으로 초기화
            sch[i]=0;

        machine[m]=task[0][1];     //첫번째 task 배정
        sch[0]=1;    //첫번쨰 task 배정 확인
        System.out.println("Machine 1: task 1: start : "+task[0][0]+ " finish : "+task[0][1]);

        for(int i=1; i<task.length; i++){     //모든 task가 배정될때까지 반복
            for(int k=0; k<=m; k++){        //사용중인 기계를 확인하여 작업이 끝난 기계가 있는지 확인
                if(sch[i]==0 && task[i][0]>=machine[k]) {
                    machine[k]=task[i][1];      //사용이 끝난 기계가 있으면 다음 task의 finish 시간을 넣어줌
                    sch[i]=1;
                    System.out.println("Machine "+(k+1)+": task "+(i+1)+": start : "+task[i][0]+ " finish : "+task[i][1]);
                }
            }
            if(sch[i]==0){     //사용중인 기계를 다 확인해도 끝난 기계가 없으면 새로운 기계에 배정
                m++;
                machine[m]=task[i][1];
                System.out.println("Machine "+(m+1)+": task "+(i+1)+": start : "+task[i][0]+ " finish : "+task[i][1]);
            }
        }
        System.out.println("사용된 Machine의 개수 : "+(m+1));
        }


    public static void main(String[] args) {
        int[][] task = {{7,8}, {3,7}, {1,5}, {5,9}, {0,2}, {6,8}, {1,6}};

        XmergeSort(task,0,task.length-1);    //시작시간에 대해 오름차순으로 정렬

       JobScheduling(task);
    }
}
