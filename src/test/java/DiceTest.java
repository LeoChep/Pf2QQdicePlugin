import java.util.Random;

public class DiceTest {
    public static  void  main(String args[])
    {
        int n =2;                 // n表示骰子个数
        int x[] = {0, 20, 20,20,20,20,20,20,20,20,20,20,20,20,20};     // x保存每个骰子的最大点数
        
        double dp[][] = new double[100][100];         // dp[i][j]表示第总共i个骰子，掷出的结果为j的概率

        // 初始化动态规划数组
        for (int i = 0; i <= 50; i++)
        {
            for (int j = 0; j <= 50; j++)
            {
                dp[i][j] = 0;
            }
        }

        dp[0][0] = 1;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= x[i]; j++)
            {
                double tmp = 0;
                for (int k = 0; k <= j; k++)
                {
                    tmp += dp[i-1][k];
                }
                dp[i][j] += tmp/x[i];

                for (int k = j + 1; k <= 50; k++)
                {
                    dp[i][k] += dp[i-1][k]/x[i];
                }
            }

            for (int j = x[i] + 1; j <= 50; j++)
            {
                dp[i][j] = dp[i-1][j];
            }
        }

        // 计算期望值
        double ans = 0 ;
        for (int i = 0; i <= 50; i++)
        {
            ans += dp[n][i] * i;
        }

        System.out.println(ans);
        ans=0;
        for (int i = 0; i <= 1000000; i++)
        {
            Random random=new Random();
            int temp1=random.nextInt(20)+1;
            int temp2=random.nextInt(20)+1;
            if (temp1<temp2) temp1=temp2;
            ans += temp1;
        }
        ans/=1000000;
        System.out.println(ans);
    }
}
