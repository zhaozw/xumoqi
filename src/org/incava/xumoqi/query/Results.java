/*
  XuMoQi - word game program
  Copyright (C) 2014  Jeff Pace

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along
  with this program; if not, write to the Free Software Foundation, Inc.,
  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

  See https://raw.github.com/jpace/xumoqi/master/LICENSE for the full license.

  The full source code for this program is available at:
  http://github.com/jpace/xumoqi

  This program includes code from the GPL'd program:
  http://sourceforge.net/projects/scrabbledict/
*/

package org.incava.xumoqi.query;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.os.Parcel;
import android.os.Parcelable;

public class Results implements Parcelable {
	public static final int MAX_SCORE = 100;
	
    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        public Results createFromParcel(Parcel parcel) {
            return new Results(parcel);
        }
        
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    private final TreeSet<String> correct;
    private final TreeSet<String> missed;
    private final TreeSet<String> invalid;

    public Results(List<String> matching, List<String> responseList) {
        correct = new TreeSet<String>();
        missed = new TreeSet<String>();
        invalid = new TreeSet<String>();

        Set<String> all = new TreeSet<String>(matching);
        all.addAll(responseList);

        for (String str : all) {
            Set<String> set = matching.contains(str) ? (responseList.contains(str) ? correct : missed) : invalid;
            set.add(str);
        }
    }
    
    protected Results(Parcel parcel) {
        correct = readStringSet(parcel);
        missed = readStringSet(parcel);
        invalid = readStringSet(parcel);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
    	writeStringSet(parcel, correct);
    	writeStringSet(parcel, missed);
    	writeStringSet(parcel, invalid);
    }
    
    public TreeSet<String> getCorrect() {
        return correct;
    }

    public TreeSet<String> getMissed() {
        return missed;
    }

    public TreeSet<String> getInvalid() {
        return invalid;
    }

    public String toString() {
        return "correct: " + correct + "; invalid: " + invalid + "; missed: " + missed;
    }
    
    public boolean isCorrect() {
   		return getInvalid().isEmpty() && getMissed().isEmpty();
    }
    
    public String inspect() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("correct: ").append(correct).append('\n');
    	sb.append("invalid: ").append(invalid).append('\n');
    	sb.append("missed: ").append(missed).append('\n');
    	return sb.toString();
    }
    
    public int getScore() {
    	// @TODO refine this algorithm:
    	int total = correct.size() + missed.size() + invalid.size();
    	return (100 * correct.size()) / total; 
    }

    private void writeStringSet(Parcel parcel, TreeSet<String> set) {
    	parcel.writeStringArray(set.toArray(new String[set.size()]));
    }
    
    private TreeSet<String> readStringSet(Parcel parcel) {
    	String[] strAry = parcel.createStringArray();
    	List<String> list = Arrays.asList(strAry);
    	return new TreeSet<String>(list);
    }
}