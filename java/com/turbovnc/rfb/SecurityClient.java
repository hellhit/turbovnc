/* Copyright (C) 2002-2005 RealVNC Ltd.  All Rights Reserved.
 * Copyright (C) 2010 TigerVNC Team
 * Copyright (C) 2011-2012 Brian P. Hinz
 * Copyright (C) 2012 D. R. Commander.  All Rights Reserved.
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

package com.turbovnc.rfb;

import com.turbovnc.vncviewer.CConn;

public class SecurityClient extends Security {

  public SecurityClient() { super(secTypes); }

  public CSecurity GetCSecurity(int secType)
  {
    assert (CConn.upg != null); /* (upg == null) means bug in the viewer */
    assert (msg != null);
  
    if (!IsSupported(secType))
      throw new Exception("Security type not supported");
  
    switch (secType) {
    case Security.secTypeNone: return (new CSecurityNone());
    case Security.secTypeVncAuth: return (new CSecurityVncAuth());
    case Security.secTypeTight: return (new CSecurityTight(this));
    case Security.secTypeVeNCrypt: return (new CSecurityVeNCrypt(this));
    case Security.secTypePlain: return (new CSecurityPlain());
    case Security.secTypeIdent: return (new CSecurityIdent());
    case Security.secTypeTLSNone:
      return (new CSecurityStack(secTypeTLSNone, "TLSNone",
  			      new CSecurityTLS(true), null));
    case Security.secTypeTLSVnc:
      return (new CSecurityStack(secTypeTLSVnc, "TLSVnc",
  			      new CSecurityTLS(true), new CSecurityVncAuth()));
    case Security.secTypeTLSPlain:
      return (new CSecurityStack(secTypeTLSPlain, "TLSPlain",
  			      new CSecurityTLS(true), new CSecurityPlain()));
    case Security.secTypeTLSIdent:
      return (new CSecurityStack(secTypeTLSIdent, "TLSIdent",
  			      new CSecurityTLS(true), new CSecurityIdent()));
    case Security.secTypeX509None:
      return (new CSecurityStack(secTypeX509None, "X509None",
  			      new CSecurityTLS(false), null));
    case Security.secTypeX509Vnc:
      return (new CSecurityStack(secTypeX509Vnc, "X509Vnc",
  			      new CSecurityTLS(false), new CSecurityVncAuth()));
    case Security.secTypeX509Plain:
      return (new CSecurityStack(secTypeX509Plain, "X509Plain",
  			      new CSecurityTLS(false), new CSecurityPlain()));
    case Security.secTypeX509Ident:
      return (new CSecurityStack(secTypeX509Ident, "X509Ident",
  			      new CSecurityTLS(false), new CSecurityIdent()));
    default:
      throw new Exception("Security type not supported");
    }
  
  }

  public static void setDefaults()
  {
      CSecurityTLS.setDefaults();
  }

  String msg = null;

  public static StringParameter secTypes 
  = new StringParameter("SecurityTypes",
  "A comma-separated list of the VeNCrypt security types that can be used, if "+
  "the server supports them.  The 12 supported security types (None, VncAuth, "+
  "Plain, Ident, TLSNone, TLSVnc, TLSPlain, TLSIdent, X509None, X509Vnc, "+
  "X509Plain, and X509Ident) are combinations of three encryption methods "+
  "(None, Anonymous TLS, and TLS with X.509 certificates) and four "+
  "authentication schemes (None, Standard VNC, Plain, and Ident.)  \"Plain\" "+
  "authenticates using a plain text user name and password, so it is strongly "+
  "recommended that it only be used with encrypted connections.  \"Ident\" "+
  "authenticates using only a user name.  The order of this list does not "+
  "matter, since the server's preferred order is always used.",
  "X509Plain,X509Ident,X509Vnc,X509None,TLSPlain,TLSIdent,TLSVnc,TLSNone,VncAuth,Ident,Plain,None");

}