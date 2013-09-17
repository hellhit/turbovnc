/* Copyright (C) 2013 D. R. Commander.  All Rights Reserved.
 *
 * This is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,
 * USA.
 */

package com.turbovnc.vncviewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ProfileDialog extends Dialog {

  public ProfileDialog(CConn cc_) {
    super(false);
    cc = cc_;
    setTitle("TurboVNC profiling information");
    setResizable(false);

    JPanel panel = new JPanel(new GridBagLayout());

    JLabel recvHeading = new JLabel("Recv");
    Font font = recvHeading.getFont();
    Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    recvHeading.setFont(boldFont);
    JLabel decodeHeading = new JLabel("Decode");
    font = decodeHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    decodeHeading.setFont(boldFont);
    JLabel blitHeading = new JLabel("Blit");
    font = blitHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    blitHeading.setFont(boldFont);
    JLabel totalHeading = new JLabel("Total");
    font = totalHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    totalHeading.setFont(boldFont);

    JLabel upsHeading = new JLabel("Updates/sec:");
    font = upsHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    upsHeading.setFont(boldFont);
    upsVal = new JLabel("000.000");

    JLabel tpHeading = new JLabel("Throughput (Mbits/sec):");
    font = tpHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    tpHeading.setFont(boldFont);
    tpVal = new JLabel("000.000");

    JLabel tpuHeading = new JLabel("Time/update (ms):");
    font = tpuHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    tpuHeading.setFont(boldFont);
    tpuRecvVal = new JLabel("000.000");
    tpuDecodeVal = new JLabel("000.000");
    tpuBlitVal = new JLabel("000.000");
    tpuTotalVal = new JLabel("000.000");

    JLabel mpHeading = new JLabel("Mpixels:");
    font = mpHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    mpHeading.setFont(boldFont);
    mpDecodeVal = new JLabel("000.000");
    mpBlitVal = new JLabel("000.000");

    JLabel mpsHeading = new JLabel("Mpixels/sec:");
    font = mpsHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    mpsHeading.setFont(boldFont);
    mpsDecodeVal = new JLabel("000.000");
    mpsBlitVal = new JLabel("000.000");
    mpsTotalVal = new JLabel("000.000");

    JLabel rectHeading = new JLabel("Rectangles:");
    font = rectHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    rectHeading.setFont(boldFont);
    rectDecodeVal = new JLabel("0000000");
    rectBlitVal = new JLabel("0000000");

    JLabel pprHeading = new JLabel("Pixels/rect:");
    font = pprHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    pprHeading.setFont(boldFont);
    pprDecodeVal = new JLabel("0000000");
    pprBlitVal = new JLabel("0000000");

    JLabel rpuHeading = new JLabel("Rects/update:");
    font = rpuHeading.getFont();
    boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
    rpuHeading.setFont(boldFont);
    rpuDecodeVal = new JLabel("0000000");

    addGBComponent(recvHeading, panel,
                   1, 0, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(decodeHeading, panel,
                   2, 0, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.WEST,
                   new Insets(2, 8, 2, 8));
    addGBComponent(blitHeading, panel,
                   3, 0, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.WEST,
                   new Insets(2, 8, 2, 8));
    addGBComponent(totalHeading, panel,
                   4, 0, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.WEST,
                   new Insets(2, 8, 2, 8));

    addGBComponent(upsHeading, panel,
                   0, 1, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(upsVal, panel,
                   4, 1, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.WEST,
                   new Insets(2, 8, 2, 8));

    addGBComponent(tpHeading, panel,
                   0, 2, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(tpVal, panel,
                   1, 2, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.WEST,
                   new Insets(2, 8, 2, 8));

    addGBComponent(tpuHeading, panel,
                   0, 3, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(tpuRecvVal, panel,
                   1, 3, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(tpuDecodeVal, panel,
                   2, 3, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(tpuBlitVal, panel,
                   3, 3, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(tpuTotalVal, panel,
                   4, 3, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));

    addGBComponent(mpHeading, panel,
                   0, 4, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(mpDecodeVal, panel,
                   2, 4, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(mpBlitVal, panel,
                   3, 4, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));

    addGBComponent(mpsHeading, panel,
                   0, 5, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(mpsDecodeVal, panel,
                   2, 5, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(mpsBlitVal, panel,
                   3, 5, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(mpsTotalVal, panel,
                   4, 5, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));

    addGBComponent(rectHeading, panel,
                   0, 6, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(rectDecodeVal, panel,
                   2, 6, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(rectBlitVal, panel,
                   3, 6, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));

    addGBComponent(pprHeading, panel,
                   0, 7, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(pprDecodeVal, panel,
                   2, 7, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(pprBlitVal, panel,
                   3, 7, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));

    addGBComponent(rpuHeading, panel,
                   0, 8, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));
    addGBComponent(rpuDecodeVal, panel,
                   2, 8, 1, 1, 0, 0, 0, 0,
                   GridBagConstraints.NONE,
                   GridBagConstraints.LINE_START,
                   new Insets(2, 8, 2, 8));

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.getContentPane().add(panel);
    pack();

    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        endDialog();
        cc.toggleProfile();
      }
    };
    KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    this.getRootPane().registerKeyboardAction(actionListener, ks,
      JComponent.WHEN_IN_FOCUSED_WINDOW);
    ks = KeyStroke.getKeyStroke(KeyEvent.VK_P,
      KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK | KeyEvent.SHIFT_MASK);
    this.getRootPane().registerKeyboardAction(actionListener, ks,
      JComponent.WHEN_IN_FOCUSED_WINDOW);
    if (VncViewer.os.startsWith("mac os x")) {
      int appleKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();     
      ks = KeyStroke.getKeyStroke(KeyEvent.VK_P, appleKey);
      this.getRootPane().registerKeyboardAction(actionListener, ks,
        JComponent.WHEN_IN_FOCUSED_WINDOW);
    }      
  }

  CConn cc;
  public JLabel upsVal, tpVal;
  public JLabel tpuRecvVal, tpuDecodeVal, tpuBlitVal, tpuTotalVal;
  public JLabel mpDecodeVal, mpBlitVal, mpsDecodeVal, mpsBlitVal, mpsTotalVal;
  public JLabel rectDecodeVal, rectBlitVal, pprDecodeVal, pprBlitVal;
  public JLabel rpuDecodeVal;
}