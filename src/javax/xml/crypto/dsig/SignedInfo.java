/*
 * Copyright (c) 2005, 2011, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
/*
 * $Id: SignedInfo.java,v 1.7 2005/05/10 16:03:47 mullan Exp $
 */
package javax.xml.crypto.dsig;

import javax.xml.crypto.XMLStructure;
import java.io.InputStream;
import java.util.List;

/**
 * An representation of the XML <code>SignedInfo</code> element as
 * defined in the <a href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendation for XML-Signature Syntax and Processing</a>.
 * The XML Schema Definition is defined as:
 * <pre><code>
 * <element name="SignedInfo" type="ds:SignedInfoType"/>
 * <complexType name="SignedInfoType">
 *   <sequence>
 *     <element ref="ds:CanonicalizationMethod"/>
 *     <element ref="ds:SignatureMethod"/>
 *     <element ref="ds:Reference" maxOccurs="unbounded"/>
 *   </sequence>
 *   <attribute name="Id" type="ID" use="optional"/>
 * </complexType>
 * </code></pre>
 *
 * A <code>SignedInfo</code> instance may be created by invoking one of the
 * {@link XMLSignatureFactory#newSignedInfo newSignedInfo} methods of the
 * {@link XMLSignatureFactory} class.
 *
 * @author Sean Mullan
 * @author JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignatureFactory#newSignedInfo(CanonicalizationMethod, SignatureMethod, List)
 * @see XMLSignatureFactory#newSignedInfo(CanonicalizationMethod, SignatureMethod, List, String)
 */
public interface SignedInfo extends XMLStructure {

    /**
     * Returns the canonicalization method of this <code>SignedInfo</code>.
     *
     * @return the canonicalization method
     */
    CanonicalizationMethod getCanonicalizationMethod();

    /**
     * Returns the signature method of this <code>SignedInfo</code>.
     *
     * @return the signature method
     */
    SignatureMethod getSignatureMethod();

    /**
     * Returns an {@link java.util.Collections#unmodifiableList
     * unmodifiable list} of one or more {@link Reference}s.
     *
     * @return an unmodifiable list of one or more {@link Reference}s
     */
    @SuppressWarnings("rawtypes")
    List getReferences();

    /**
     * Returns the optional <code>Id</code> attribute of this
     * <code>SignedInfo</code>.
     *
     * @return the id (may be <code>null</code> if not specified)
     */
    String getId();

    /**
     * Returns the canonicalized signed info bytes after a signing or
     * validation operation. This method is useful for debugging.
     *
     * @return an <code>InputStream</code> containing the canonicalized bytes,
     *    or <code>null</code> if this <code>SignedInfo</code> has not been
     *    signed or validated yet
     */
    InputStream getCanonicalizedData();
}
