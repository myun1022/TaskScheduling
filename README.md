# JobScheduling


Job Scheduling 문제는 작업의 수행 시간이 중복되지 않도록 모든 작업을 가장 적은 수의 기계에 배정하는 문제이다.


+ 입력값 : n개의 작업 t1, t2, …, tn
+ 출력값 : 각 기계에 배정된 작업 순서


-------

1. 시작시간에 대해 작업을 오름차순으로 정렬한다.
```java
static void mergeSort(job[] A, int p, int q) {      //합병정렬을 사용하여 작업의 시작시간 오름차순으로 정렬
        if (p >= q) return;
        int k = (p + q) / 2;

        mergeSort(A, p, k);
        mergeSort(A, k + 1, q);
        merge(A, p, k, q);
    }

    static void merge(job[] A, int p, int k, int q) {     //합병정렬
        job[] C = new job[q - p + 1];
        int i = p, j = k + 1, n = 0;

        while (i <= k && j <= q) {
            C[n++] = A[i].s < A[j].s ? A[i++] : A[j++];

        }
        while (i <= k) {
            C[n++] = A[i++];
        }
        while (j <= q) {
            C[n++] = A[j++];
        }
        for (int a = p, b = 0; a <= q; a++) {
            A[a] = C[b++];
        }
    }
```
`MergeSort` 알고리즘을 이용해 각 작업들의 시작시간에 대해 오름차순으로 정렬한다.


-------

2. 정렬된 작업들을 중복되지 않게 수행할 수 있는 기계를 찾아 배정한다.
```java
 mergeSort(L, 0, n - 1);      //시작시간의 오름차순으로 정렬된 배열 L
        job[][] machine = new job[n][n];      //수행시간이 중복되지 않게 작업을 배정할 2차원 배열 machine

        boolean[] check = new boolean[n];      //작업 배정 여부를 확인할 배열 check
        for (int i = 0; i < n; i++) {
            int count = 0;     //작업의 순서
            int finish = 0;     //가장 마지막 작업의 끝시간
            for (int j = 0; j < n; j++)
                if (L[j].s >= finish && !check[j]) {       //아직 배정되지 않은 작업의 시작시간이 기계에 배정된 작업의 끝시간과 같거나 그 이후일 경우
                    machine[i][count] = L[j];           //작업 배정
                    finish = machine[i][count].f;        //가장 마지막 작업의 끝시간 갱신
                    check[j] = true;
                    count++;
                }
        }
```
정렬된 배열 `L`에서 아직 작업이 배정되지 않았으며, 가장 이른 시작시간을 가진 작업을 수행시간이 중복되지 않게 수행할 기계를 찾아 배정한다.
기존의 기계들에 작업을 배정할 수 없는 경우에는 새로운 기계에 작업을 배정한다.
작업이 배정되면 배열 `check`를 이용해 배정되었음을 확정해주어 더 이상 해당 작업이 배정에 고려되지 않도록 한다.
이를 `L`에 있는 모든 작업들이 배정될 때까지 반복해서 수행한다.

-------

3. 각 기계에 배정된 작업들의 순서를 출력한다.
```java
 for (int i = 0; i < n; i++) {
            //기계 별로 배정된 작업을 순서대로 출력
            if (machine[i][0] == null) break;
            System.out.print("기계 " + (i + 1) + " : ");
            for (int j = 0; j < n; j++) {
                if (machine[i][j] == null)
                    break;
                System.out.print("[" + machine[i][j].s + ", " + machine[i][j].f + "]");
            }
            System.out.println();
        }
```

-------

## 시간복잡도
> *n*개의 작업을 배정하는 데 사용된 기계의 수가 *m*개라고 한다면, JobScheduling 알고리즘의 시간복잡도는 O(*n*log*n*)+O(*mn*)

+ *n*개의 작업을 정렬하는 데 O(*n*log*n*) 시간이 걸린다.
+ 작업을 `L`에서 가져다가 수행 가능한 기계를 찾아 배정하므로 O(*m*) 시간이 걸리고, 이 방식이 *n*번 반복되므로 총 O(*m*)×*n*=O(*mn*) 시간이 걸린다.

**따라서 시간복잡도는 O(*n*log*n*)+O(*mn*) 이다.**