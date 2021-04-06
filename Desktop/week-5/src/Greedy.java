//'c언어로 쉽게 풀어쓴 자료구조' 교재 참고함
// 4/7 완성

public class Greedy {

    static final int INF = 1000000;
    static int[] distance = new int[10];    //거리를 저장할 배열
    static int[] found = new int[10];    //방문된 노드를 표시할 배열
    static String[] node = {"서울", "천안", "원주", "강릉", "논산", "대전", "대구", "포항", "광주", "부산"};
    static int step = 1;

    static void Print(int[][] d) {
        System.out.print("distance : ");
        for (int i = 0; i < d.length; i++) {
            if (distance[i] == INF) System.out.print(" * ");
            else System.out.print(distance[i] + " ");
        }
        System.out.println();
        System.out.print("found : ");
        for (int i = 0; i < d.length; i++)
            System.out.print(found[i] + " ");
        System.out.println("\n");
    }

    static int Choose(int k) {
        int min = INF;       //최단거리 초기값
        int minpos = -1;      //최단거리 인덱스 초기값
        for (int i = 0; i < k; i++)
            if (distance[i] < min && found[i] == 0) {     //최단거리 값, 배열 인덱스 위치 찾기
                min = distance[i];
                minpos = i;
            }
        return minpos;
    }

    static void ShortestPath(int[][] d, int start) {
        System.out.println("STEP " + step + ": ");
        step++;
        System.out.println("출발점 : " + node[start]);

        for (int i = 0; i < d.length; i++) {
            distance[i] = d[start][i];      //시작 노드부터의 거리 저장
            found[i] = 0;               //방문된 노드 배열 0으로 초기화
        }

        found[start] = 1;       //시작 노드 방문 표시

        int u = 0;
        while (u < d.length - 1) {
            Print(d);
            int a = Choose(d.length);     //가장 가까운 노드 선택
            System.out.println(node[a]+ " 방문");
            found[a] = 1;     //선택된 노드 방문 표시
            for (int i = 0; i < d.length; i++)   //최단거리(distance[] 확정)
                if (found[i] == 0)
                    if (distance[a] + d[a][i] < distance[i])  //선택된 노드를 통한 노드i까지의 거리가 distance[i]보다 작으면 그 값으로 대입
                        distance[i] = distance[a] + d[a][i];
            u++;
        }
        Print(d);
    }

    public static void main(String[] args) {

        int[][] path = {
                {0, 12, 15, INF, INF, INF, INF, INF, INF, INF},
                {12, 0, INF, INF, 4, 10, INF, INF, INF, INF},
                {15, INF, 0, 21, INF, INF, 7, INF, INF, INF},
                {INF, INF, 21, 0, INF, INF, INF, 25, INF, INF},
                {INF, 4, INF, INF, 0, 3, INF, INF, 13, INF},
                {INF, 10, INF, INF, 3, 0, 10, INF, INF, INF},
                {INF, INF, 7, INF, INF, 10, 0, 19, INF, 9},
                {INF, INF, INF, 25, INF, INF, 19, 0, INF, 5},
                {INF, INF, INF, INF, 13, INF, INF, INF, 0, 15},
                {INF, INF, INF, INF, INF, INF, 9, 5, 15, 0}};

        for (int i = 0; i < path.length; i++)
            System.out.print(node[i] + " ");
        System.out.println("\n");

        ShortestPath(path, 0);
    }
}
