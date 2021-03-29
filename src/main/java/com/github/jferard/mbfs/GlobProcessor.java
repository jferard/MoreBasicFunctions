/*
 * MoreBasicFunctions - Java - UNO component that provides more basic functions.
 * Copyright (C) 2021 Julien Férard.
 *
 * MoreBasicFunctions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MoreBasicFunctions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.jferard.mbfs;

import java.util.Arrays;

public class GlobProcessor {
    private static final int STATE_NORMAL = 0;
    private static final int STATE_IN_BRACKET = 1;
    private static final int STATE_IN_LIST = 2;

    private int len;
    private StringBuilder sb;
    private int i;
    private String pattern;
    private int state;
    private int code;
    private int s;
    private int[] states;


    public GlobProcessor(String pattern) {
        this.pattern = pattern;
        this.states = new int[this.pattern.length() * 2];
    }

    public String process() {
        this.sb = new StringBuilder().append('^');
        this.i = 0;
        this.s = 0;
        this.len = pattern.length();
        pushState(STATE_NORMAL);
        while (i < len) {
            char c = get();
            if (state == STATE_NORMAL) {
                if (c == '*') {
                    if (peek() == '(') {
                        sb.append(get());
                        pushState(STATE_IN_LIST, c);
                    } else {
                        sb.append(".*");
                    }
                } else if (c == '?') {
                    if (peek() == '(') {
                        sb.append(get());
                        pushState(STATE_IN_LIST, c);
                    } else {
                        sb.append(".");
                    }
                } else if (c == '+') {
                    if (peek() == '(') {
                        sb.append(get());
                        pushState(STATE_IN_LIST, c);
                    } else {
                        sb.append("\\+");
                    }
                } else if (c == '@') {
                    if (peek() == '(') {
                        sb.append(get());
                        pushState(STATE_IN_LIST, c);
                    } else {
                        sb.append(c);
                    }
                } else if (c == '!') {
                    if (peek() == '(') {
                        sb.append(get()).append("?!");
                        pushState(STATE_IN_LIST, c);
                    } else {
                        sb.append(c);
                    }
                } else {
                    handle(c);
                }
            } else if (state == STATE_IN_LIST) {
                if (c == ')') {
                    sb.append(')');
                    if (code != '!' && code != '@') {
                        sb.append((char) code);
                    }
                    popState();
                } else {
                    handle(c);
                }
            } else if (state == STATE_IN_BRACKET) {
                sb.append(c);
                if (c == ']') {
                    popState();
                }
            }
        }
        sb.append('$');
        return sb.toString();
    }

    private void handle(char c) {
        if (c == '[') {
            int p = peek();
            if (p == -1) {
                sb.append("\\[");
            } else {
                sb.append(c);
                if (p == '^' || p == '!') {
                    get();
                    sb.append('^');
                }
                p = peek();
                if (p == ']') {
                    sb.append('\\').append(get());
                }
                pushState(STATE_IN_BRACKET, c);
            }
        } else {
            if (c == '(' || c == ')' || c == ']' || c == '\\' || c == '\'' || c == '"' || c == '.') {
                sb.append('\\');
            }
            sb.append(c);
        }
    }

    private void pushState(int state) {
        pushState(state, -1);
    }

    private void pushState(int state, int code) {
        this.states[this.s++] = state;
        this.states[this.s++] = code;
        this.state = state;
        this.code = code;
    }

    private void popState() {
        this.s -= 2;
        this.state = this.states[this.s - 2];
        this.code = this.states[this.s - 1];
    }

    private int peek() {
        return this.i < this.len ? pattern.charAt(this.i) : -1;
    }

    private char get() {
        char c = pattern.charAt(i);
        this.i++;
        return c;
    }
}
