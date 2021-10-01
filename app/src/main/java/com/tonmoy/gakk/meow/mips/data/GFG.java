package com.tonmoy.gakk.meow.mips.data;

class GFG
{

    // Returns '0' for '1' and '1' for '0'
    static char flip(char c)
    {
        return (c == '0') ? '1' : '0';
    }

    // Print 1's and 2's complement of binary number
    // represented by "bin"
    static String printTwosComplement(String bin)
    {

        int n = bin.length();
        int i;

        String ones = "", twos = "";
        ones = twos = "";

        // for ones complement flip every bit
        for (i = 0; i < n; i++)
        {
            ones += flip(bin.charAt(i));
        }

        // for two's complement go from right to left in
        // ones complement and if we get 1 make, we make
        // them 0 and keep going left when we get first
        // 0, make that 1 and go out of loop
        twos = ones;
        for (i = n - 1; i >= 0; i--)
        {
            if (ones.charAt(i) == '1')
            {
                twos = twos.substring(0, i) + '0' + twos.substring(i + 1);
            }
            else
            {
                twos = twos.substring(0, i) + '1' + twos.substring(i + 1);
                break;
            }
        }

        // If No break : all are 1 as in 111 or 11111;
        // in such case, add extra 1 at beginning
        if (i == -1)
        {
            twos = '1' + twos;
        }

        //System.out.println("1's complement: " + ones);;
      //  System.out.println("2's complement: " + twos);
        return twos;
    }
    static String addBinary(String a, String b)
    {

        // Initialize result
        String result = "";

        // Initialize digit sum
        int s = 0;

        // Traverse both strings starting
        // from last characters
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || s == 1)
        {

            // Comput sum of last
            // digits and carry
            s += ((i >= 0)? a.charAt(i) - '0': 0);
            s += ((j >= 0)? b.charAt(j) - '0': 0);

            // If current digit sum is
            // 1 or 3, add 1 to result
            result = (char)(s % 2 + '0') + result;

            // Compute carry
            s /= 2;

            // Move to next digits
            i--; j--;
        }

        return result;
    }

    // Driver code
    public static void main(String[] args)
    {
        String bin = "1100";
       // printOneAndTwosComplement(bin);
    }

}
class Comp{
    private String oneS;
    private String twoS;

    public String getOneS() {
        return oneS;
    }

    public void setOneS(String oneS) {
        this.oneS = oneS;
    }

    public String getTwoS() {
        return twoS;
    }

    public void setTwoS(String twoS) {
        this.twoS = twoS;
    }
}
