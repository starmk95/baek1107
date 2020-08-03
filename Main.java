import java.util.*;

public class Main {
    static boolean[] brokenBtn = new boolean[10];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int channel = sc.nextInt();
        int brokenNum = sc.nextInt();
        int ans = Math.abs(channel-100);
        for (int i=0;i<brokenNum;i++) {
            brokenBtn[sc.nextInt()] = true;
        }
        // 브루트 포스
        // <예외처리 목록>
        // 100번으로 이동은 숫자버튼 누를 필요 없다.
        // 101번으로 이동은 + 1번 누르는 것이 최소
        // 102번으로 이동은 + 2번 누르는 것이 최소
        // 99번으로 이동은 - 1번 누르는 것이 최소
        if (channel == 100) ans = 0;
        else if (channel == 101 || channel == 99) ans = 1;
        else if (channel == 102 || channel == 98) ans = 2;
        else if (channel == 103) ans = 3;
        else {
            // 고장나지 않은 숫자를 눌러서 접근할 수 있는 수 중 가장 가까운 수를 찾아야 함
            // 모든 채널에서 이동하려는 채널로 이동하는 모든 경우의 수를 찾기 (브루트 포스)
            for (int i=0;i<=1000000;i++) { // 최악의 경우 50만번 + 또는 -눌러야 하므로 100만까지 고려
                int temp = i;
                int pressNum = possible(temp); // 고장난 버튼을 포함하지 않는 채널이면 숫자버튼을 누르는 횟수 반환 그렇지 않으면 0 반환
                int plusMinus = Math.abs(channel-temp);
                if (pressNum>0) { // 해당 채널을 입력할 수 있다면
                    if (ans > plusMinus+pressNum) { // 더 적은 횟수로 이동 가능
                        ans = plusMinus+pressNum;
                    }
                }
            }
        }
        System.out.println(ans);
    }
    // 해당 채널이 고장난 버튼을 포함하는지 확인하는 함수
    // 고장난 버튼 포함하면 0 반환, 입력 가능하면 숫자 버튼 입력 횟수 출력
    static int possible(int num) {
        int n = num;
        if (num == 0) { // 0번 예외처리
            if (brokenBtn[0]) return 0;
            else return 1;
        }
        while (n>0) {
            if (brokenBtn[n%10]) return 0; // 고장난 버튼을 포함
            n/=10;
        }
        // 고장난 버튼 없이 입력이 가능한 경우
        return (int)(Math.log10(num)+1); // 자리수만큼 버튼 누르므로 자리수 출력
    }
}
