package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class LumpSumData {

    private Set<LumpSumEntry> lumpSumList;

    /**
     * It fetch cumulative lumpSum amount up to the specified emiNumber, assuming there are multiple
     * lumsum payment for a given user for a specified bank
     * @param eMINumber
     * @return
     */
    public Double fetchCumulativeSumUptoEmiNumber(Integer eMINumber){
        Double cumSum = 0.0;
        Iterator<LumpSumEntry> iterator = lumpSumList.iterator();
        while(iterator.hasNext()){
            LumpSumEntry lumpSumEntry = iterator.next();
            if(lumpSumEntry.getEMINumber() <=eMINumber){
                cumSum+=lumpSumEntry.getAmount();

            }else{
                break;
            }
        }
        return cumSum;


    }

    public LumpSumData() {
        this.lumpSumList = new TreeSet<>();
    }

    public LumpSumData(LumpSumEntry data) {
        this.lumpSumList = new TreeSet<>();
        this.lumpSumList.add(data);

    }

    public LumpSumData(List<LumpSumEntry> data) {
        this.lumpSumList = new TreeSet<>();
        lumpSumList.addAll(data);

    }

    public void addData(LumpSumEntry lumSumData) {
        lumpSumList.add(lumSumData);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class LumpSumEntry implements Comparable<LumpSumEntry>{
        private Double amount;
        private Integer eMINumber;

        @Override
        public int compareTo(LumpSumEntry o) {
            if(eMINumber > o.getEMINumber()){
                return 1;
            }else if (eMINumber < o.getEMINumber()){
                return -1;

            }else{
                return 0;
            }
        }
    }


}
