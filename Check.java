package check;
 import java.util.Scanner;
public class Check {
 public final static int MAX = 25;
 public static int n = 0, r = 16;
 public static int[] crc = new int[MAX], input = new int[MAX], poly = {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 
 0, 1, 0, 0, 0, 0, 1};
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 int i,j,num,pos,ch;
 boolean flag;
 System.out.println("Enter the no of bits in the message");
 n=sc.nextInt();
 System.out.println("\n\nEnter the binary message");
 for(i = 0; i < n; i++)
 input[i] = sc.nextInt();
 for( i = n; i < n + r; i++)
 input[i] = 0;
 genCrc();
 System.out.println("\nGenerated CRC is:");
 for( i = n, j = 0; i < n + r; i++) {
 input[i] = crc[j++];
 System.out.print(input[i] + " ");
 }
 System.out.println("\nTransmitted code word is:");
 for( i = 0; i < n + r; i++)
 System.out.print(input[i] + " ");
 System.out.print("\n\nDo you want to make an error(1/0): ");
 ch = sc.nextInt();
 if(ch = = 1) {
 System.out.println("Enter the position");
 pos = sc.nextInt();
 if(pos > 0 && pos < n + r)
 makeerror(pos);
 else {
 System.out.println("Invalid position; Message is not alterred \n");
 System.exit(0);
 }
}
System.out.println("\nReceived code word is:");
 for( i = 0; i < n + r; i++)
 System.out.printf("%2d", input[i]);
genCrc();
 System.out.println("\nGeneratedCRC is:");
 for( i = n, j = 0; i < n + r; i++) {
 System.out.printf("% d", crc[j]);
input[i] = crc[j++];
}
flag = false;
for(i = 0; i < r; i++)
if(crc[i] != 0) {
flag = true;
break;
}
if(!flag)
System.out.println("\n\nMessage has no error");
else
System.out.print("Message received has an Error\\n");
}
public static void genCrc() {
int i, j, k, s;
System.out.println();
for(i = 0; i <= r; i++ /* consider first r+1 bits of input */) {
crc[i] = input[i];
}
while(i <= n + r /* until all bits of input are considered */) {
k = 0;
j = 0;
if(crc[k] == poly[j]) {
while(k <= r) {
crc[k] = crc[k++] ^ poly[j++];
}
} else {
while(k <= r) {
crc[k] = crc[k++] ^ 0;
}
}
for(k = 1; k <= r; k++) {
crc[k - 1] = crc[k];
}
crc[k - 1] = input[i++];
}
}
public static void makeerror(int pos) {
if(input[pos] == 1) {
input[pos] = 0;
} else {
input[pos] = 1;
}
}
}
