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

package org.incava.xumoqi.games;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Current iteration and history of games (queries) executed.
 * Game:
 *     type (e.g., random blanks)
 *     word length
 *     queries: {
 *         query pattern: {
 *             matching
 *             iterations: [
 *                 response
 *                 score (response v. matching)
 *             ]
 *         }
 *         history: [ query patterns ]
 *     } 
 * 
 * @author jpace
 */
public class GameIterations implements Parcelable {
    public static final Parcelable.Creator<GameIterations> CREATOR = new Parcelable.Creator<GameIterations>() {
        public GameIterations createFromParcel(Parcel parcel) {
            return new GameIterations(parcel);
        }
        
        public GameIterations[] newArray(int size) {
            return new GameIterations[size];
        }
    };

    private final int wordLength;
    private final String gameType;
    private final ArrayList<GameIteration> iterations;
    
	public GameIterations(int wordLength, String gameType) {
		this.wordLength = wordLength;
		this.gameType = gameType;
		this.iterations = new ArrayList<GameIteration>(); 
	}

    private GameIterations(Parcel parcel) {
        this(parcel.readInt(), parcel.readString());
        parcel.readList(this.iterations, GameIteration.class.getClassLoader());
    }

    public int getWordLength() {
        return wordLength;
    }

    public String getGameType() {
        return gameType;
    }
    
    public List<GameIteration> getIterations() {
    	return iterations;
    }

    public void addIteration(GameIteration it) {
    	iterations.add(it);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(wordLength);
        parcel.writeString(gameType);
        parcel.writeList(iterations);
    }
    
    public String toString() {
        return "wordLength: " + wordLength + "; gameType: " + gameType;
    }
}