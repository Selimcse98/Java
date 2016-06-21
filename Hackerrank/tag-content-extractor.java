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



//============= using Regex =============
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        Pattern pattern = Pattern.compile(
                "<([/a-zA-Z]+).*?>"
        );
        while(testCases>0) {
            String line = in.nextLine();
            Matcher matcher = pattern.matcher(line);
            List<String> tags = new ArrayList<String>();
            while(matcher.find()) {
                String tag = matcher.group(0);
                tags.add(tag);
            }
            boolean none = true;
            while(tags.size() >= 2) {
                String tagB = tags.get(0);
                String tagE = tags.get(1);
                if (!tagB.replace("<", "</").equals(tagE)) {
                    tags.remove(0);
                    int x = line.indexOf(tagB);
                    line = line.substring(x + tagB.length());
                }
                else {
                    tags.remove(0);
                    tags.remove(0);
                    int x = line.indexOf(tagB) + tagB.length();
                    int y = line.indexOf(tagE, x);
                    String finding = line.substring(x,y);
                    line = line.substring(y + tagE.length());
                    if (!finding.isEmpty()) {
                        System.out.println(finding);
                        none = false;
                    }
                }
            }
            if (none) {
                System.out.println("None");
            }
            testCases--;
        }
    }
}

