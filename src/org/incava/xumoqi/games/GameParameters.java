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

import org.incava.xumoqi.query.QueryList;
import org.incava.xumoqi.query.Response;
import org.incava.xumoqi.utils.Constants;

import android.content.Intent;

public class GameParameters {
    public static QueryList getQueryList(Intent intent) {
        return intent.getParcelableExtra(Constants.QUERIES);
    }
    
    public static void saveQueryList(Intent intent, QueryList queryList) {
        intent.putExtra(Constants.QUERIES, queryList);
    }

    public static GameIterations getGameIterations(Intent intent) {
        return intent.getParcelableExtra(Constants.GAME_ITERATIONS);
    }

    public static void saveGameIterations(Intent intent, GameIterations gameIterations) {
        intent.putExtra(Constants.GAME_ITERATIONS, gameIterations);
    }

    public static int getQueryIndex(Intent intent) {
        return intent.getIntExtra(Constants.QUERY_INDEX, -1);
    }

    public static void saveQueryIndex(Intent intent, int queryIndex) {
        intent.putExtra(Constants.QUERY_INDEX, queryIndex);    
    }

    public static Response getResponse(Intent intent) {
        return intent.getParcelableExtra(Constants.RESPONSE);
    }

    public static void saveResponse(Intent intent, Response gameIterations) {
        intent.putExtra(Constants.RESPONSE, gameIterations);
    }
    
    public static ArrayList<String> getMatching(Intent intent) {
        return intent.getStringArrayListExtra(Constants.MATCHING);
    }

    public static void saveMatching(Intent intent, ArrayList<String> matching) {
        intent.putStringArrayListExtra(Constants.MATCHING, matching);
    }

    public static long getDuration(Intent intent) {
        String durStr = intent.getStringExtra(Constants.DURATION);
        return Long.valueOf(durStr);
    }
    
    public static void saveDuration(Intent intent, long duration) {
        String durStr = String.valueOf(duration);
        intent.putExtra(Constants.DURATION, durStr);
    }
}
