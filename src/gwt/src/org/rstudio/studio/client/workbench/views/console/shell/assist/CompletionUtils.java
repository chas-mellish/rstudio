/*
 * CompletionUtils.java
 *
 * Copyright (C) 2009-12 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */


package org.rstudio.studio.client.workbench.views.console.shell.assist;

import org.rstudio.core.client.command.KeyboardShortcut;
import org.rstudio.studio.client.workbench.views.console.shell.editor.InputEditorDisplay;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;

public class CompletionUtils
{
   public static boolean isCompletionRequest(NativeEvent event, int modifier)
   {
      return (event.getKeyCode() == KeyCodes.KEY_TAB && modifier == KeyboardShortcut.NONE)
            || (event.getKeyCode() == KeyCodes.KEY_SPACE && modifier == KeyboardShortcut.CTRL);
   }

   public static boolean handleEncloseSelection(InputEditorDisplay input, 
                                                char c)
   {
      if (!input.isSelectionCollapsed())
      {
         switch(c)
         {
         case '"':
         case '\'':
            encloseSelection(input, c, c);
            return true;
         case '(':
            encloseSelection(input, '(', ')');
            return true;
         case '{':
            encloseSelection(input, '{', '}');
            return true;
         case '[':
            encloseSelection(input, '[', ']');
            return true;  
         default:
            return false;
         }
      }
      else
      {
         return false;
      }
   }
   
   
   
   private static void encloseSelection(InputEditorDisplay input,
                                        char beginChar, 
                                        char endChar) 
   {
      StringBuilder builder = new StringBuilder();
      builder.append(beginChar);
      builder.append(input.getSelectionValue());
      builder.append(endChar);
      input.replaceSelection(builder.toString(), true);
   }
}
