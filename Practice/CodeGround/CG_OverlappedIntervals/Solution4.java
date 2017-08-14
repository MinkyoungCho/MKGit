/*
 * 모범답안 
 */
package CG_OverlappedIntervals;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution4 {
    public static int[] rightestOf;
    public static Interval[] intervals;
    public static int N;

    static class Interval implements Comparable<Interval> {
        int left;
        int right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Interval o) {
            if(this.left == o.left)
                return o.right - this.right; // right 에 대해서 내림차순 (ot내)
            else
                return this.left - o.left; // left에 대하여 오름차순 (to오)
        }
    }

    public static void main(String args[]) throws Exception	{
        Scanner sc = new Scanner(new FileInputStream("input.txt"));

        int TC;
        int test_case;

        TC = sc.nextInt();
        for(test_case = 1; test_case <= TC; test_case++) {

            N = sc.nextInt();
            rightestOf = new int[N];
            intervals = new Interval[N];

            for(int i = 0; i < N; i++)
                intervals[i] = new Interval(sc.nextInt(), sc.nextInt());

            Arrays.sort(intervals);

            int max = 0;

            for (int i = 0; i < N; ++i) {
                int possibleMin = 0, possibleMax = max;
                while (possibleMin < possibleMax) {
                    int mid = (possibleMin + possibleMax) / 2;
                    if (intervals[i].right <= rightestOf[mid]) possibleMin = mid + 1;
                    else possibleMax = mid;
                }

                rightestOf[possibleMax] = intervals[i].right;
                if (possibleMax == max) max++;
            }

            System.out.println("Case #" + test_case);
            System.out.println(max);
        }
    }
}