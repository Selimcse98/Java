import java.util.Scanner;

class Solution{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){
            String line=in.nextLine();
            int index=0;
            boolean none=true;
            for(;;){
                int start=line.indexOf("<",index);
                if(start<0)
                    break;
                int end=line.indexOf(">",start);
                if(end<0)
                    break;
                String tag=line.substring(start+1,end);
                if(tag.length()==0 || tag.charAt(0)=='/'){
                    index=end+1;
                    continue;
                }
                int index_endTag=line.indexOf("</"+tag+">");
                if(index_endTag>=0){
                    String candidate=line.substring(end+1,index_endTag);
                    if(candidate.length()>0 && candidate.indexOf("<")<0){
                        none=false;
                        System.out.println(candidate);
                    }
                }
                index=end+1;
            }
            if(none)
                System.out.println("None");
            testCases--;
        }
    }
}
