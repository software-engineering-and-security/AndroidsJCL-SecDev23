<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 80ade7e8b392 src/share/classes/sun/security/x509/X500Name.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/80ade7e8b392">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/80ade7e8b392">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/80ade7e8b392">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/80ade7e8b392/src/share/classes/sun/security/x509/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/x509/X500Name.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/80ade7e8b392/src/share/classes/sun/security/x509/X500Name.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/80ade7e8b392/src/share/classes/sun/security/x509/X500Name.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/80ade7e8b392/src/share/classes/sun/security/x509/X500Name.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/80ade7e8b392/src/share/classes/sun/security/x509/X500Name.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/80ade7e8b392/src/share/classes/sun/security/x509/X500Name.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/x509/X500Name.java @ 13797:80ade7e8b392</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8234037: Improve Object Identifier Processing
Reviewed-by: weijun, mschoene, ssahoo</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#120;&#117;&#101;&#108;&#101;&#105;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Mon, 25 Nov 2019 16:55:54 -0800</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/cd9f7780d964/src/share/classes/sun/security/x509/X500Name.java">cd9f7780d964</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"></td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1996, 2019, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Oracle designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Oracle in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
<span id="l10"> *</span><a href="#l10"></a>
<span id="l11"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l11"></a>
<span id="l12"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l12"></a>
<span id="l13"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l13"></a>
<span id="l14"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l14"></a>
<span id="l15"> * accompanied this code).</span><a href="#l15"></a>
<span id="l16"> *</span><a href="#l16"></a>
<span id="l17"> * You should have received a copy of the GNU General Public License version</span><a href="#l17"></a>
<span id="l18"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l18"></a>
<span id="l19"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l19"></a>
<span id="l20"> *</span><a href="#l20"></a>
<span id="l21"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l21"></a>
<span id="l22"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l22"></a>
<span id="l23"> * questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25"></span><a href="#l25"></a>
<span id="l26">package sun.security.x509;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.lang.reflect.*;</span><a href="#l28"></a>
<span id="l29">import java.io.IOException;</span><a href="#l29"></a>
<span id="l30">import java.security.PrivilegedExceptionAction;</span><a href="#l30"></a>
<span id="l31">import java.security.AccessController;</span><a href="#l31"></a>
<span id="l32">import java.security.Principal;</span><a href="#l32"></a>
<span id="l33">import java.util.*;</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">import sun.security.util.*;</span><a href="#l35"></a>
<span id="l36">import javax.security.auth.x500.X500Principal;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38">/**</span><a href="#l38"></a>
<span id="l39"> * Note:  As of 1.4, the public class,</span><a href="#l39"></a>
<span id="l40"> * javax.security.auth.x500.X500Principal,</span><a href="#l40"></a>
<span id="l41"> * should be used when parsing, generating, and comparing X.500 DNs.</span><a href="#l41"></a>
<span id="l42"> * This class contains other useful methods for checking name constraints</span><a href="#l42"></a>
<span id="l43"> * and retrieving DNs by keyword.</span><a href="#l43"></a>
<span id="l44"> *</span><a href="#l44"></a>
<span id="l45"> * &lt;p&gt; X.500 names are used to identify entities, such as those which are</span><a href="#l45"></a>
<span id="l46"> * identified by X.509 certificates.  They are world-wide, hierarchical,</span><a href="#l46"></a>
<span id="l47"> * and descriptive.  Entities can be identified by attributes, and in</span><a href="#l47"></a>
<span id="l48"> * some systems can be searched for according to those attributes.</span><a href="#l48"></a>
<span id="l49"> * &lt;p&gt;</span><a href="#l49"></a>
<span id="l50"> * The ASN.1 for this is:</span><a href="#l50"></a>
<span id="l51"> * &lt;pre&gt;</span><a href="#l51"></a>
<span id="l52"> * GeneralName ::= CHOICE {</span><a href="#l52"></a>
<span id="l53"> * ....</span><a href="#l53"></a>
<span id="l54"> *     directoryName                   [4]     Name,</span><a href="#l54"></a>
<span id="l55"> * ....</span><a href="#l55"></a>
<span id="l56"> * Name ::= CHOICE {</span><a href="#l56"></a>
<span id="l57"> *   RDNSequence }</span><a href="#l57"></a>
<span id="l58"> *</span><a href="#l58"></a>
<span id="l59"> * RDNSequence ::= SEQUENCE OF RelativeDistinguishedName</span><a href="#l59"></a>
<span id="l60"> *</span><a href="#l60"></a>
<span id="l61"> * RelativeDistinguishedName ::=</span><a href="#l61"></a>
<span id="l62"> *   SET OF AttributeTypeAndValue</span><a href="#l62"></a>
<span id="l63"> *</span><a href="#l63"></a>
<span id="l64"> * AttributeTypeAndValue ::= SEQUENCE {</span><a href="#l64"></a>
<span id="l65"> *   type     AttributeType,</span><a href="#l65"></a>
<span id="l66"> *   value    AttributeValue }</span><a href="#l66"></a>
<span id="l67"> *</span><a href="#l67"></a>
<span id="l68"> * AttributeType ::= OBJECT IDENTIFIER</span><a href="#l68"></a>
<span id="l69"> *</span><a href="#l69"></a>
<span id="l70"> * AttributeValue ::= ANY DEFINED BY AttributeType</span><a href="#l70"></a>
<span id="l71"> * ....</span><a href="#l71"></a>
<span id="l72"> * DirectoryString ::= CHOICE {</span><a href="#l72"></a>
<span id="l73"> *       teletexString           TeletexString (SIZE (1..MAX)),</span><a href="#l73"></a>
<span id="l74"> *       printableString         PrintableString (SIZE (1..MAX)),</span><a href="#l74"></a>
<span id="l75"> *       universalString         UniversalString (SIZE (1..MAX)),</span><a href="#l75"></a>
<span id="l76"> *       utf8String              UTF8String (SIZE (1.. MAX)),</span><a href="#l76"></a>
<span id="l77"> *       bmpString               BMPString (SIZE (1..MAX)) }</span><a href="#l77"></a>
<span id="l78"> * &lt;/pre&gt;</span><a href="#l78"></a>
<span id="l79"> * &lt;p&gt;</span><a href="#l79"></a>
<span id="l80"> * This specification requires only a subset of the name comparison</span><a href="#l80"></a>
<span id="l81"> * functionality specified in the X.500 series of specifications.  The</span><a href="#l81"></a>
<span id="l82"> * requirements for conforming implementations are as follows:</span><a href="#l82"></a>
<span id="l83"> * &lt;ol TYPE=a&gt;</span><a href="#l83"></a>
<span id="l84"> * &lt;li&gt;attribute values encoded in different types (e.g.,</span><a href="#l84"></a>
<span id="l85"> *    PrintableString and BMPString) may be assumed to represent</span><a href="#l85"></a>
<span id="l86"> *    different strings;</span><a href="#l86"></a>
<span id="l87"> * &lt;p&gt;</span><a href="#l87"></a>
<span id="l88"> * &lt;li&gt;attribute values in types other than PrintableString are case</span><a href="#l88"></a>
<span id="l89"> *    sensitive (this permits matching of attribute values as binary</span><a href="#l89"></a>
<span id="l90"> *    objects);</span><a href="#l90"></a>
<span id="l91"> * &lt;p&gt;</span><a href="#l91"></a>
<span id="l92"> * &lt;li&gt;attribute values in PrintableString are not case sensitive</span><a href="#l92"></a>
<span id="l93"> *    (e.g., &quot;Marianne Swanson&quot; is the same as &quot;MARIANNE SWANSON&quot;); and</span><a href="#l93"></a>
<span id="l94"> * &lt;p&gt;</span><a href="#l94"></a>
<span id="l95"> * &lt;li&gt;attribute values in PrintableString are compared after</span><a href="#l95"></a>
<span id="l96"> *    removing leading and trailing white space and converting internal</span><a href="#l96"></a>
<span id="l97"> *    substrings of one or more consecutive white space characters to a</span><a href="#l97"></a>
<span id="l98"> *    single space.</span><a href="#l98"></a>
<span id="l99"> * &lt;/ol&gt;</span><a href="#l99"></a>
<span id="l100"> * &lt;p&gt;</span><a href="#l100"></a>
<span id="l101"> * These name comparison rules permit a certificate user to validate</span><a href="#l101"></a>
<span id="l102"> * certificates issued using languages or encodings unfamiliar to the</span><a href="#l102"></a>
<span id="l103"> * certificate user.</span><a href="#l103"></a>
<span id="l104"> * &lt;p&gt;</span><a href="#l104"></a>
<span id="l105"> * In addition, implementations of this specification MAY use these</span><a href="#l105"></a>
<span id="l106"> * comparison rules to process unfamiliar attribute types for name</span><a href="#l106"></a>
<span id="l107"> * chaining. This allows implementations to process certificates with</span><a href="#l107"></a>
<span id="l108"> * unfamiliar attributes in the issuer name.</span><a href="#l108"></a>
<span id="l109"> * &lt;p&gt;</span><a href="#l109"></a>
<span id="l110"> * Note that the comparison rules defined in the X.500 series of</span><a href="#l110"></a>
<span id="l111"> * specifications indicate that the character sets used to encode data</span><a href="#l111"></a>
<span id="l112"> * in distinguished names are irrelevant.  The characters themselves are</span><a href="#l112"></a>
<span id="l113"> * compared without regard to encoding. Implementations of the profile</span><a href="#l113"></a>
<span id="l114"> * are permitted to use the comparison algorithm defined in the X.500</span><a href="#l114"></a>
<span id="l115"> * series.  Such an implementation will recognize a superset of name</span><a href="#l115"></a>
<span id="l116"> * matches recognized by the algorithm specified above.</span><a href="#l116"></a>
<span id="l117"> * &lt;p&gt;</span><a href="#l117"></a>
<span id="l118"> * Note that instances of this class are immutable.</span><a href="#l118"></a>
<span id="l119"> *</span><a href="#l119"></a>
<span id="l120"> * @author David Brownell</span><a href="#l120"></a>
<span id="l121"> * @author Amit Kapoor</span><a href="#l121"></a>
<span id="l122"> * @author Hemma Prafullchandra</span><a href="#l122"></a>
<span id="l123"> * @see GeneralName</span><a href="#l123"></a>
<span id="l124"> * @see GeneralNames</span><a href="#l124"></a>
<span id="l125"> * @see GeneralNameInterface</span><a href="#l125"></a>
<span id="l126"> */</span><a href="#l126"></a>
<span id="l127"></span><a href="#l127"></a>
<span id="l128">public class X500Name implements GeneralNameInterface, Principal {</span><a href="#l128"></a>
<span id="l129"></span><a href="#l129"></a>
<span id="l130">    private String dn; // roughly RFC 1779 DN, or null</span><a href="#l130"></a>
<span id="l131">    private String rfc1779Dn; // RFC 1779 compliant DN, or null</span><a href="#l131"></a>
<span id="l132">    private String rfc2253Dn; // RFC 2253 DN, or null</span><a href="#l132"></a>
<span id="l133">    private String canonicalDn; // canonical RFC 2253 DN or null</span><a href="#l133"></a>
<span id="l134">    private RDN[] names;        // RDNs (never null)</span><a href="#l134"></a>
<span id="l135">    private X500Principal x500Principal;</span><a href="#l135"></a>
<span id="l136">    private byte[] encoded;</span><a href="#l136"></a>
<span id="l137"></span><a href="#l137"></a>
<span id="l138">    // cached immutable list of the RDNs and all the AVAs</span><a href="#l138"></a>
<span id="l139">    private volatile List&lt;RDN&gt; rdnList;</span><a href="#l139"></a>
<span id="l140">    private volatile List&lt;AVA&gt; allAvaList;</span><a href="#l140"></a>
<span id="l141"></span><a href="#l141"></a>
<span id="l142">    /**</span><a href="#l142"></a>
<span id="l143">     * Constructs a name from a conventionally formatted string, such</span><a href="#l143"></a>
<span id="l144">     * as &quot;CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US&quot;.</span><a href="#l144"></a>
<span id="l145">     * (RFC 1779, 2253, or 4514 style).</span><a href="#l145"></a>
<span id="l146">     *</span><a href="#l146"></a>
<span id="l147">     * @param dname the X.500 Distinguished Name</span><a href="#l147"></a>
<span id="l148">     */</span><a href="#l148"></a>
<span id="l149">    public X500Name(String dname) throws IOException {</span><a href="#l149"></a>
<span id="l150">        this(dname, Collections.&lt;String, String&gt;emptyMap());</span><a href="#l150"></a>
<span id="l151">    }</span><a href="#l151"></a>
<span id="l152"></span><a href="#l152"></a>
<span id="l153">    /**</span><a href="#l153"></a>
<span id="l154">     * Constructs a name from a conventionally formatted string, such</span><a href="#l154"></a>
<span id="l155">     * as &quot;CN=Dave, OU=JavaSoft, O=Sun Microsystems, C=US&quot;.</span><a href="#l155"></a>
<span id="l156">     * (RFC 1779, 2253, or 4514 style).</span><a href="#l156"></a>
<span id="l157">     *</span><a href="#l157"></a>
<span id="l158">     * @param dname the X.500 Distinguished Name</span><a href="#l158"></a>
<span id="l159">     * @param keywordMap an additional keyword/OID map</span><a href="#l159"></a>
<span id="l160">     */</span><a href="#l160"></a>
<span id="l161">    public X500Name(String dname, Map&lt;String, String&gt; keywordMap)</span><a href="#l161"></a>
<span id="l162">        throws IOException {</span><a href="#l162"></a>
<span id="l163">        parseDN(dname, keywordMap);</span><a href="#l163"></a>
<span id="l164">    }</span><a href="#l164"></a>
<span id="l165"></span><a href="#l165"></a>
<span id="l166">    /**</span><a href="#l166"></a>
<span id="l167">     * Constructs a name from a string formatted according to format.</span><a href="#l167"></a>
<span id="l168">     * Currently, the formats DEFAULT and RFC2253 are supported.</span><a href="#l168"></a>
<span id="l169">     * DEFAULT is the default format used by the X500Name(String)</span><a href="#l169"></a>
<span id="l170">     * constructor. RFC2253 is the format strictly according to RFC2253</span><a href="#l170"></a>
<span id="l171">     * without extensions.</span><a href="#l171"></a>
<span id="l172">     *</span><a href="#l172"></a>
<span id="l173">     * @param dname the X.500 Distinguished Name</span><a href="#l173"></a>
<span id="l174">     * @param format the specified format of the String DN</span><a href="#l174"></a>
<span id="l175">     */</span><a href="#l175"></a>
<span id="l176">    public X500Name(String dname, String format) throws IOException {</span><a href="#l176"></a>
<span id="l177">        if (dname == null) {</span><a href="#l177"></a>
<span id="l178">            throw new NullPointerException(&quot;Name must not be null&quot;);</span><a href="#l178"></a>
<span id="l179">        }</span><a href="#l179"></a>
<span id="l180">        if (format.equalsIgnoreCase(&quot;RFC2253&quot;)) {</span><a href="#l180"></a>
<span id="l181">            parseRFC2253DN(dname);</span><a href="#l181"></a>
<span id="l182">        } else if (format.equalsIgnoreCase(&quot;DEFAULT&quot;)) {</span><a href="#l182"></a>
<span id="l183">            parseDN(dname, Collections.&lt;String, String&gt;emptyMap());</span><a href="#l183"></a>
<span id="l184">        } else {</span><a href="#l184"></a>
<span id="l185">            throw new IOException(&quot;Unsupported format &quot; + format);</span><a href="#l185"></a>
<span id="l186">        }</span><a href="#l186"></a>
<span id="l187">    }</span><a href="#l187"></a>
<span id="l188"></span><a href="#l188"></a>
<span id="l189">    /**</span><a href="#l189"></a>
<span id="l190">     * Constructs a name from fields common in enterprise application</span><a href="#l190"></a>
<span id="l191">     * environments.</span><a href="#l191"></a>
<span id="l192">     *</span><a href="#l192"></a>
<span id="l193">     * &lt;P&gt;&lt;EM&gt;&lt;STRONG&gt;NOTE:&lt;/STRONG&gt;  The behaviour when any of</span><a href="#l193"></a>
<span id="l194">     * these strings contain characters outside the ASCII range</span><a href="#l194"></a>
<span id="l195">     * is unspecified in currently relevant standards.&lt;/EM&gt;</span><a href="#l195"></a>
<span id="l196">     *</span><a href="#l196"></a>
<span id="l197">     * @param commonName common name of a person, e.g. &quot;Vivette Davis&quot;</span><a href="#l197"></a>
<span id="l198">     * @param organizationUnit small organization name, e.g. &quot;Purchasing&quot;</span><a href="#l198"></a>
<span id="l199">     * @param organizationName large organization name, e.g. &quot;Onizuka, Inc.&quot;</span><a href="#l199"></a>
<span id="l200">     * @param country two letter country code, e.g. &quot;CH&quot;</span><a href="#l200"></a>
<span id="l201">     */</span><a href="#l201"></a>
<span id="l202">    public X500Name(String commonName, String organizationUnit,</span><a href="#l202"></a>
<span id="l203">                     String organizationName, String country)</span><a href="#l203"></a>
<span id="l204">    throws IOException {</span><a href="#l204"></a>
<span id="l205">        names = new RDN[4];</span><a href="#l205"></a>
<span id="l206">        /*</span><a href="#l206"></a>
<span id="l207">         * NOTE:  it's only on output that little-endian</span><a href="#l207"></a>
<span id="l208">         * ordering is used.</span><a href="#l208"></a>
<span id="l209">         */</span><a href="#l209"></a>
<span id="l210">        names[3] = new RDN(1);</span><a href="#l210"></a>
<span id="l211">        names[3].assertion[0] = new AVA(commonName_oid,</span><a href="#l211"></a>
<span id="l212">                new DerValue(commonName));</span><a href="#l212"></a>
<span id="l213">        names[2] = new RDN(1);</span><a href="#l213"></a>
<span id="l214">        names[2].assertion[0] = new AVA(orgUnitName_oid,</span><a href="#l214"></a>
<span id="l215">                new DerValue(organizationUnit));</span><a href="#l215"></a>
<span id="l216">        names[1] = new RDN(1);</span><a href="#l216"></a>
<span id="l217">        names[1].assertion[0] = new AVA(orgName_oid,</span><a href="#l217"></a>
<span id="l218">                new DerValue(organizationName));</span><a href="#l218"></a>
<span id="l219">        names[0] = new RDN(1);</span><a href="#l219"></a>
<span id="l220">        names[0].assertion[0] = new AVA(countryName_oid,</span><a href="#l220"></a>
<span id="l221">                new DerValue(country));</span><a href="#l221"></a>
<span id="l222">    }</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">    /**</span><a href="#l224"></a>
<span id="l225">     * Constructs a name from fields common in Internet application</span><a href="#l225"></a>
<span id="l226">     * environments.</span><a href="#l226"></a>
<span id="l227">     *</span><a href="#l227"></a>
<span id="l228">     * &lt;P&gt;&lt;EM&gt;&lt;STRONG&gt;NOTE:&lt;/STRONG&gt;  The behaviour when any of</span><a href="#l228"></a>
<span id="l229">     * these strings contain characters outside the ASCII range</span><a href="#l229"></a>
<span id="l230">     * is unspecified in currently relevant standards.&lt;/EM&gt;</span><a href="#l230"></a>
<span id="l231">     *</span><a href="#l231"></a>
<span id="l232">     * @param commonName common name of a person, e.g. &quot;Vivette Davis&quot;</span><a href="#l232"></a>
<span id="l233">     * @param organizationUnit small organization name, e.g. &quot;Purchasing&quot;</span><a href="#l233"></a>
<span id="l234">     * @param organizationName large organization name, e.g. &quot;Onizuka, Inc.&quot;</span><a href="#l234"></a>
<span id="l235">     * @param localityName locality (city) name, e.g. &quot;Palo Alto&quot;</span><a href="#l235"></a>
<span id="l236">     * @param stateName state name, e.g. &quot;California&quot;</span><a href="#l236"></a>
<span id="l237">     * @param country two letter country code, e.g. &quot;CH&quot;</span><a href="#l237"></a>
<span id="l238">     */</span><a href="#l238"></a>
<span id="l239">    public X500Name(String commonName, String organizationUnit,</span><a href="#l239"></a>
<span id="l240">                    String organizationName, String localityName,</span><a href="#l240"></a>
<span id="l241">                    String stateName, String country)</span><a href="#l241"></a>
<span id="l242">    throws IOException {</span><a href="#l242"></a>
<span id="l243">        names = new RDN[6];</span><a href="#l243"></a>
<span id="l244">        /*</span><a href="#l244"></a>
<span id="l245">         * NOTE:  it's only on output that little-endian</span><a href="#l245"></a>
<span id="l246">         * ordering is used.</span><a href="#l246"></a>
<span id="l247">         */</span><a href="#l247"></a>
<span id="l248">        names[5] = new RDN(1);</span><a href="#l248"></a>
<span id="l249">        names[5].assertion[0] = new AVA(commonName_oid,</span><a href="#l249"></a>
<span id="l250">                new DerValue(commonName));</span><a href="#l250"></a>
<span id="l251">        names[4] = new RDN(1);</span><a href="#l251"></a>
<span id="l252">        names[4].assertion[0] = new AVA(orgUnitName_oid,</span><a href="#l252"></a>
<span id="l253">                new DerValue(organizationUnit));</span><a href="#l253"></a>
<span id="l254">        names[3] = new RDN(1);</span><a href="#l254"></a>
<span id="l255">        names[3].assertion[0] = new AVA(orgName_oid,</span><a href="#l255"></a>
<span id="l256">                new DerValue(organizationName));</span><a href="#l256"></a>
<span id="l257">        names[2] = new RDN(1);</span><a href="#l257"></a>
<span id="l258">        names[2].assertion[0] = new AVA(localityName_oid,</span><a href="#l258"></a>
<span id="l259">                new DerValue(localityName));</span><a href="#l259"></a>
<span id="l260">        names[1] = new RDN(1);</span><a href="#l260"></a>
<span id="l261">        names[1].assertion[0] = new AVA(stateName_oid,</span><a href="#l261"></a>
<span id="l262">                new DerValue(stateName));</span><a href="#l262"></a>
<span id="l263">        names[0] = new RDN(1);</span><a href="#l263"></a>
<span id="l264">        names[0].assertion[0] = new AVA(countryName_oid,</span><a href="#l264"></a>
<span id="l265">                new DerValue(country));</span><a href="#l265"></a>
<span id="l266">    }</span><a href="#l266"></a>
<span id="l267"></span><a href="#l267"></a>
<span id="l268">    /**</span><a href="#l268"></a>
<span id="l269">     * Constructs a name from an array of relative distinguished names</span><a href="#l269"></a>
<span id="l270">     *</span><a href="#l270"></a>
<span id="l271">     * @param rdnArray array of relative distinguished names</span><a href="#l271"></a>
<span id="l272">     * @throws IOException on error</span><a href="#l272"></a>
<span id="l273">     */</span><a href="#l273"></a>
<span id="l274">    public X500Name(RDN[] rdnArray) throws IOException {</span><a href="#l274"></a>
<span id="l275">        if (rdnArray == null) {</span><a href="#l275"></a>
<span id="l276">            names = new RDN[0];</span><a href="#l276"></a>
<span id="l277">        } else {</span><a href="#l277"></a>
<span id="l278">            names = rdnArray.clone();</span><a href="#l278"></a>
<span id="l279">            for (int i = 0; i &lt; names.length; i++) {</span><a href="#l279"></a>
<span id="l280">                if (names[i] == null) {</span><a href="#l280"></a>
<span id="l281">                    throw new IOException(&quot;Cannot create an X500Name&quot;);</span><a href="#l281"></a>
<span id="l282">                }</span><a href="#l282"></a>
<span id="l283">            }</span><a href="#l283"></a>
<span id="l284">        }</span><a href="#l284"></a>
<span id="l285">    }</span><a href="#l285"></a>
<span id="l286"></span><a href="#l286"></a>
<span id="l287">    /**</span><a href="#l287"></a>
<span id="l288">     * Constructs a name from an ASN.1 encoded value.  The encoding</span><a href="#l288"></a>
<span id="l289">     * of the name in the stream uses DER (a BER/1 subset).</span><a href="#l289"></a>
<span id="l290">     *</span><a href="#l290"></a>
<span id="l291">     * @param value a DER-encoded value holding an X.500 name.</span><a href="#l291"></a>
<span id="l292">     */</span><a href="#l292"></a>
<span id="l293">    public X500Name(DerValue value) throws IOException {</span><a href="#l293"></a>
<span id="l294">        //Note that toDerInputStream uses only the buffer (data) and not</span><a href="#l294"></a>
<span id="l295">        //the tag, so an empty SEQUENCE (OF) will yield an empty DerInputStream</span><a href="#l295"></a>
<span id="l296">        this(value.toDerInputStream());</span><a href="#l296"></a>
<span id="l297">    }</span><a href="#l297"></a>
<span id="l298"></span><a href="#l298"></a>
<span id="l299">    /**</span><a href="#l299"></a>
<span id="l300">     * Constructs a name from an ASN.1 encoded input stream.  The encoding</span><a href="#l300"></a>
<span id="l301">     * of the name in the stream uses DER (a BER/1 subset).</span><a href="#l301"></a>
<span id="l302">     *</span><a href="#l302"></a>
<span id="l303">     * @param in DER-encoded data holding an X.500 name.</span><a href="#l303"></a>
<span id="l304">     */</span><a href="#l304"></a>
<span id="l305">    public X500Name(DerInputStream in) throws IOException {</span><a href="#l305"></a>
<span id="l306">        parseDER(in);</span><a href="#l306"></a>
<span id="l307">    }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">    /**</span><a href="#l309"></a>
<span id="l310">     *  Constructs a name from an ASN.1 encoded byte array.</span><a href="#l310"></a>
<span id="l311">     *</span><a href="#l311"></a>
<span id="l312">     * @param name DER-encoded byte array holding an X.500 name.</span><a href="#l312"></a>
<span id="l313">     */</span><a href="#l313"></a>
<span id="l314">    public X500Name(byte[] name) throws IOException {</span><a href="#l314"></a>
<span id="l315">        DerInputStream in = new DerInputStream(name);</span><a href="#l315"></a>
<span id="l316">        parseDER(in);</span><a href="#l316"></a>
<span id="l317">    }</span><a href="#l317"></a>
<span id="l318"></span><a href="#l318"></a>
<span id="l319">    /**</span><a href="#l319"></a>
<span id="l320">     * Return an immutable List of all RDNs in this X500Name.</span><a href="#l320"></a>
<span id="l321">     */</span><a href="#l321"></a>
<span id="l322">    public List&lt;RDN&gt; rdns() {</span><a href="#l322"></a>
<span id="l323">        List&lt;RDN&gt; list = rdnList;</span><a href="#l323"></a>
<span id="l324">        if (list == null) {</span><a href="#l324"></a>
<span id="l325">            list = Collections.unmodifiableList(Arrays.asList(names));</span><a href="#l325"></a>
<span id="l326">            rdnList = list;</span><a href="#l326"></a>
<span id="l327">        }</span><a href="#l327"></a>
<span id="l328">        return list;</span><a href="#l328"></a>
<span id="l329">    }</span><a href="#l329"></a>
<span id="l330"></span><a href="#l330"></a>
<span id="l331">    /**</span><a href="#l331"></a>
<span id="l332">     * Return the number of RDNs in this X500Name.</span><a href="#l332"></a>
<span id="l333">     */</span><a href="#l333"></a>
<span id="l334">    public int size() {</span><a href="#l334"></a>
<span id="l335">        return names.length;</span><a href="#l335"></a>
<span id="l336">    }</span><a href="#l336"></a>
<span id="l337"></span><a href="#l337"></a>
<span id="l338">    /**</span><a href="#l338"></a>
<span id="l339">     * Return an immutable List of the the AVAs contained in all the</span><a href="#l339"></a>
<span id="l340">     * RDNs of this X500Name.</span><a href="#l340"></a>
<span id="l341">     */</span><a href="#l341"></a>
<span id="l342">    public List&lt;AVA&gt; allAvas() {</span><a href="#l342"></a>
<span id="l343">        List&lt;AVA&gt; list = allAvaList;</span><a href="#l343"></a>
<span id="l344">        if (list == null) {</span><a href="#l344"></a>
<span id="l345">            list = new ArrayList&lt;AVA&gt;();</span><a href="#l345"></a>
<span id="l346">            for (int i = 0; i &lt; names.length; i++) {</span><a href="#l346"></a>
<span id="l347">                list.addAll(names[i].avas());</span><a href="#l347"></a>
<span id="l348">            }</span><a href="#l348"></a>
<span id="l349">            list = Collections.unmodifiableList(list);</span><a href="#l349"></a>
<span id="l350">            allAvaList = list;</span><a href="#l350"></a>
<span id="l351">        }</span><a href="#l351"></a>
<span id="l352">        return list;</span><a href="#l352"></a>
<span id="l353">    }</span><a href="#l353"></a>
<span id="l354"></span><a href="#l354"></a>
<span id="l355">    /**</span><a href="#l355"></a>
<span id="l356">     * Return the total number of AVAs contained in all the RDNs of</span><a href="#l356"></a>
<span id="l357">     * this X500Name.</span><a href="#l357"></a>
<span id="l358">     */</span><a href="#l358"></a>
<span id="l359">    public int avaSize() {</span><a href="#l359"></a>
<span id="l360">        return allAvas().size();</span><a href="#l360"></a>
<span id="l361">    }</span><a href="#l361"></a>
<span id="l362"></span><a href="#l362"></a>
<span id="l363">    /**</span><a href="#l363"></a>
<span id="l364">     * Return whether this X500Name is empty. An X500Name is not empty</span><a href="#l364"></a>
<span id="l365">     * if it has at least one RDN containing at least one AVA.</span><a href="#l365"></a>
<span id="l366">     */</span><a href="#l366"></a>
<span id="l367">    public boolean isEmpty() {</span><a href="#l367"></a>
<span id="l368">        int n = names.length;</span><a href="#l368"></a>
<span id="l369">        for (int i = 0; i &lt; n; i++) {</span><a href="#l369"></a>
<span id="l370">            if (names[i].assertion.length != 0) {</span><a href="#l370"></a>
<span id="l371">                return false;</span><a href="#l371"></a>
<span id="l372">            }</span><a href="#l372"></a>
<span id="l373">        }</span><a href="#l373"></a>
<span id="l374">        return true;</span><a href="#l374"></a>
<span id="l375">    }</span><a href="#l375"></a>
<span id="l376"></span><a href="#l376"></a>
<span id="l377">    /**</span><a href="#l377"></a>
<span id="l378">     * Calculates a hash code value for the object.  Objects</span><a href="#l378"></a>
<span id="l379">     * which are equal will also have the same hashcode.</span><a href="#l379"></a>
<span id="l380">     */</span><a href="#l380"></a>
<span id="l381">    public int hashCode() {</span><a href="#l381"></a>
<span id="l382">        return getRFC2253CanonicalName().hashCode();</span><a href="#l382"></a>
<span id="l383">    }</span><a href="#l383"></a>
<span id="l384"></span><a href="#l384"></a>
<span id="l385">    /**</span><a href="#l385"></a>
<span id="l386">     * Compares this name with another, for equality.</span><a href="#l386"></a>
<span id="l387">     *</span><a href="#l387"></a>
<span id="l388">     * @return true iff the names are identical.</span><a href="#l388"></a>
<span id="l389">     */</span><a href="#l389"></a>
<span id="l390">    public boolean equals(Object obj) {</span><a href="#l390"></a>
<span id="l391">        if (this == obj) {</span><a href="#l391"></a>
<span id="l392">            return true;</span><a href="#l392"></a>
<span id="l393">        }</span><a href="#l393"></a>
<span id="l394">        if (obj instanceof X500Name == false) {</span><a href="#l394"></a>
<span id="l395">            return false;</span><a href="#l395"></a>
<span id="l396">        }</span><a href="#l396"></a>
<span id="l397">        X500Name other = (X500Name)obj;</span><a href="#l397"></a>
<span id="l398">        // if we already have the canonical forms, compare now</span><a href="#l398"></a>
<span id="l399">        if ((this.canonicalDn != null) &amp;&amp; (other.canonicalDn != null)) {</span><a href="#l399"></a>
<span id="l400">            return this.canonicalDn.equals(other.canonicalDn);</span><a href="#l400"></a>
<span id="l401">        }</span><a href="#l401"></a>
<span id="l402">        // quick check that number of RDNs and AVAs match before canonicalizing</span><a href="#l402"></a>
<span id="l403">        int n = this.names.length;</span><a href="#l403"></a>
<span id="l404">        if (n != other.names.length) {</span><a href="#l404"></a>
<span id="l405">            return false;</span><a href="#l405"></a>
<span id="l406">        }</span><a href="#l406"></a>
<span id="l407">        for (int i = 0; i &lt; n; i++) {</span><a href="#l407"></a>
<span id="l408">            RDN r1 = this.names[i];</span><a href="#l408"></a>
<span id="l409">            RDN r2 = other.names[i];</span><a href="#l409"></a>
<span id="l410">            if (r1.assertion.length != r2.assertion.length) {</span><a href="#l410"></a>
<span id="l411">                return false;</span><a href="#l411"></a>
<span id="l412">            }</span><a href="#l412"></a>
<span id="l413">        }</span><a href="#l413"></a>
<span id="l414">        // definite check via canonical form</span><a href="#l414"></a>
<span id="l415">        String thisCanonical = this.getRFC2253CanonicalName();</span><a href="#l415"></a>
<span id="l416">        String otherCanonical = other.getRFC2253CanonicalName();</span><a href="#l416"></a>
<span id="l417">        return thisCanonical.equals(otherCanonical);</span><a href="#l417"></a>
<span id="l418">    }</span><a href="#l418"></a>
<span id="l419"></span><a href="#l419"></a>
<span id="l420">    /*</span><a href="#l420"></a>
<span id="l421">     * Returns the name component as a Java string, regardless of its</span><a href="#l421"></a>
<span id="l422">     * encoding restrictions.</span><a href="#l422"></a>
<span id="l423">     */</span><a href="#l423"></a>
<span id="l424">    private String getString(DerValue attribute) throws IOException {</span><a href="#l424"></a>
<span id="l425">        if (attribute == null)</span><a href="#l425"></a>
<span id="l426">            return null;</span><a href="#l426"></a>
<span id="l427">        String  value = attribute.getAsString();</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">        if (value == null)</span><a href="#l429"></a>
<span id="l430">            throw new IOException(&quot;not a DER string encoding, &quot;</span><a href="#l430"></a>
<span id="l431">                    + attribute.tag);</span><a href="#l431"></a>
<span id="l432">        else</span><a href="#l432"></a>
<span id="l433">            return value;</span><a href="#l433"></a>
<span id="l434">    }</span><a href="#l434"></a>
<span id="l435"></span><a href="#l435"></a>
<span id="l436">    /**</span><a href="#l436"></a>
<span id="l437">     * Return type of GeneralName.</span><a href="#l437"></a>
<span id="l438">     */</span><a href="#l438"></a>
<span id="l439">    public int getType() {</span><a href="#l439"></a>
<span id="l440">        return (GeneralNameInterface.NAME_DIRECTORY);</span><a href="#l440"></a>
<span id="l441">    }</span><a href="#l441"></a>
<span id="l442"></span><a href="#l442"></a>
<span id="l443">    /**</span><a href="#l443"></a>
<span id="l444">     * Returns a &quot;Country&quot; name component.  If more than one</span><a href="#l444"></a>
<span id="l445">     * such attribute exists, the topmost one is returned.</span><a href="#l445"></a>
<span id="l446">     *</span><a href="#l446"></a>
<span id="l447">     * @return &quot;C=&quot; component of the name, if any.</span><a href="#l447"></a>
<span id="l448">     */</span><a href="#l448"></a>
<span id="l449">    public String getCountry() throws IOException {</span><a href="#l449"></a>
<span id="l450">        DerValue attr = findAttribute(countryName_oid);</span><a href="#l450"></a>
<span id="l451"></span><a href="#l451"></a>
<span id="l452">        return getString(attr);</span><a href="#l452"></a>
<span id="l453">    }</span><a href="#l453"></a>
<span id="l454"></span><a href="#l454"></a>
<span id="l455"></span><a href="#l455"></a>
<span id="l456">    /**</span><a href="#l456"></a>
<span id="l457">     * Returns an &quot;Organization&quot; name component.  If more than</span><a href="#l457"></a>
<span id="l458">     * one such attribute exists, the topmost one is returned.</span><a href="#l458"></a>
<span id="l459">     *</span><a href="#l459"></a>
<span id="l460">     * @return &quot;O=&quot; component of the name, if any.</span><a href="#l460"></a>
<span id="l461">     */</span><a href="#l461"></a>
<span id="l462">    public String getOrganization() throws IOException {</span><a href="#l462"></a>
<span id="l463">        DerValue attr = findAttribute(orgName_oid);</span><a href="#l463"></a>
<span id="l464"></span><a href="#l464"></a>
<span id="l465">        return getString(attr);</span><a href="#l465"></a>
<span id="l466">    }</span><a href="#l466"></a>
<span id="l467"></span><a href="#l467"></a>
<span id="l468"></span><a href="#l468"></a>
<span id="l469">    /**</span><a href="#l469"></a>
<span id="l470">     * Returns an &quot;Organizational Unit&quot; name component.  If more</span><a href="#l470"></a>
<span id="l471">     * than one such attribute exists, the topmost one is returned.</span><a href="#l471"></a>
<span id="l472">     *</span><a href="#l472"></a>
<span id="l473">     * @return &quot;OU=&quot; component of the name, if any.</span><a href="#l473"></a>
<span id="l474">     */</span><a href="#l474"></a>
<span id="l475">    public String getOrganizationalUnit() throws IOException {</span><a href="#l475"></a>
<span id="l476">        DerValue attr = findAttribute(orgUnitName_oid);</span><a href="#l476"></a>
<span id="l477"></span><a href="#l477"></a>
<span id="l478">        return getString(attr);</span><a href="#l478"></a>
<span id="l479">    }</span><a href="#l479"></a>
<span id="l480"></span><a href="#l480"></a>
<span id="l481"></span><a href="#l481"></a>
<span id="l482">    /**</span><a href="#l482"></a>
<span id="l483">     * Returns a &quot;Common Name&quot; component.  If more than one such</span><a href="#l483"></a>
<span id="l484">     * attribute exists, the topmost one is returned.</span><a href="#l484"></a>
<span id="l485">     *</span><a href="#l485"></a>
<span id="l486">     * @return &quot;CN=&quot; component of the name, if any.</span><a href="#l486"></a>
<span id="l487">     */</span><a href="#l487"></a>
<span id="l488">    public String getCommonName() throws IOException {</span><a href="#l488"></a>
<span id="l489">        DerValue attr = findAttribute(commonName_oid);</span><a href="#l489"></a>
<span id="l490"></span><a href="#l490"></a>
<span id="l491">        return getString(attr);</span><a href="#l491"></a>
<span id="l492">    }</span><a href="#l492"></a>
<span id="l493"></span><a href="#l493"></a>
<span id="l494"></span><a href="#l494"></a>
<span id="l495">    /**</span><a href="#l495"></a>
<span id="l496">     * Returns a &quot;Locality&quot; name component.  If more than one</span><a href="#l496"></a>
<span id="l497">     * such component exists, the topmost one is returned.</span><a href="#l497"></a>
<span id="l498">     *</span><a href="#l498"></a>
<span id="l499">     * @return &quot;L=&quot; component of the name, if any.</span><a href="#l499"></a>
<span id="l500">     */</span><a href="#l500"></a>
<span id="l501">    public String getLocality() throws IOException {</span><a href="#l501"></a>
<span id="l502">        DerValue attr = findAttribute(localityName_oid);</span><a href="#l502"></a>
<span id="l503"></span><a href="#l503"></a>
<span id="l504">        return getString(attr);</span><a href="#l504"></a>
<span id="l505">    }</span><a href="#l505"></a>
<span id="l506"></span><a href="#l506"></a>
<span id="l507">    /**</span><a href="#l507"></a>
<span id="l508">     * Returns a &quot;State&quot; name component.  If more than one</span><a href="#l508"></a>
<span id="l509">     * such component exists, the topmost one is returned.</span><a href="#l509"></a>
<span id="l510">     *</span><a href="#l510"></a>
<span id="l511">     * @return &quot;S=&quot; component of the name, if any.</span><a href="#l511"></a>
<span id="l512">     */</span><a href="#l512"></a>
<span id="l513">    public String getState() throws IOException {</span><a href="#l513"></a>
<span id="l514">      DerValue attr = findAttribute(stateName_oid);</span><a href="#l514"></a>
<span id="l515"></span><a href="#l515"></a>
<span id="l516">        return getString(attr);</span><a href="#l516"></a>
<span id="l517">    }</span><a href="#l517"></a>
<span id="l518"></span><a href="#l518"></a>
<span id="l519">    /**</span><a href="#l519"></a>
<span id="l520">     * Returns a &quot;Domain&quot; name component.  If more than one</span><a href="#l520"></a>
<span id="l521">     * such component exists, the topmost one is returned.</span><a href="#l521"></a>
<span id="l522">     *</span><a href="#l522"></a>
<span id="l523">     * @return &quot;DC=&quot; component of the name, if any.</span><a href="#l523"></a>
<span id="l524">     */</span><a href="#l524"></a>
<span id="l525">    public String getDomain() throws IOException {</span><a href="#l525"></a>
<span id="l526">        DerValue attr = findAttribute(DOMAIN_COMPONENT_OID);</span><a href="#l526"></a>
<span id="l527"></span><a href="#l527"></a>
<span id="l528">        return getString(attr);</span><a href="#l528"></a>
<span id="l529">    }</span><a href="#l529"></a>
<span id="l530"></span><a href="#l530"></a>
<span id="l531">    /**</span><a href="#l531"></a>
<span id="l532">     * Returns a &quot;DN Qualifier&quot; name component.  If more than one</span><a href="#l532"></a>
<span id="l533">     * such component exists, the topmost one is returned.</span><a href="#l533"></a>
<span id="l534">     *</span><a href="#l534"></a>
<span id="l535">     * @return &quot;DNQ=&quot; component of the name, if any.</span><a href="#l535"></a>
<span id="l536">     */</span><a href="#l536"></a>
<span id="l537">    public String getDNQualifier() throws IOException {</span><a href="#l537"></a>
<span id="l538">        DerValue attr = findAttribute(DNQUALIFIER_OID);</span><a href="#l538"></a>
<span id="l539"></span><a href="#l539"></a>
<span id="l540">        return getString(attr);</span><a href="#l540"></a>
<span id="l541">    }</span><a href="#l541"></a>
<span id="l542"></span><a href="#l542"></a>
<span id="l543">    /**</span><a href="#l543"></a>
<span id="l544">     * Returns a &quot;Surname&quot; name component.  If more than one</span><a href="#l544"></a>
<span id="l545">     * such component exists, the topmost one is returned.</span><a href="#l545"></a>
<span id="l546">     *</span><a href="#l546"></a>
<span id="l547">     * @return &quot;SURNAME=&quot; component of the name, if any.</span><a href="#l547"></a>
<span id="l548">     */</span><a href="#l548"></a>
<span id="l549">    public String getSurname() throws IOException {</span><a href="#l549"></a>
<span id="l550">        DerValue attr = findAttribute(SURNAME_OID);</span><a href="#l550"></a>
<span id="l551"></span><a href="#l551"></a>
<span id="l552">        return getString(attr);</span><a href="#l552"></a>
<span id="l553">    }</span><a href="#l553"></a>
<span id="l554"></span><a href="#l554"></a>
<span id="l555">    /**</span><a href="#l555"></a>
<span id="l556">     * Returns a &quot;Given Name&quot; name component.  If more than one</span><a href="#l556"></a>
<span id="l557">     * such component exists, the topmost one is returned.</span><a href="#l557"></a>
<span id="l558">     *</span><a href="#l558"></a>
<span id="l559">     * @return &quot;GIVENNAME=&quot; component of the name, if any.</span><a href="#l559"></a>
<span id="l560">     */</span><a href="#l560"></a>
<span id="l561">    public String getGivenName() throws IOException {</span><a href="#l561"></a>
<span id="l562">       DerValue attr = findAttribute(GIVENNAME_OID);</span><a href="#l562"></a>
<span id="l563"></span><a href="#l563"></a>
<span id="l564">       return getString(attr);</span><a href="#l564"></a>
<span id="l565">    }</span><a href="#l565"></a>
<span id="l566"></span><a href="#l566"></a>
<span id="l567">    /**</span><a href="#l567"></a>
<span id="l568">     * Returns an &quot;Initials&quot; name component.  If more than one</span><a href="#l568"></a>
<span id="l569">     * such component exists, the topmost one is returned.</span><a href="#l569"></a>
<span id="l570">     *</span><a href="#l570"></a>
<span id="l571">     * @return &quot;INITIALS=&quot; component of the name, if any.</span><a href="#l571"></a>
<span id="l572">     */</span><a href="#l572"></a>
<span id="l573">    public String getInitials() throws IOException {</span><a href="#l573"></a>
<span id="l574">        DerValue attr = findAttribute(INITIALS_OID);</span><a href="#l574"></a>
<span id="l575"></span><a href="#l575"></a>
<span id="l576">        return getString(attr);</span><a href="#l576"></a>
<span id="l577">     }</span><a href="#l577"></a>
<span id="l578"></span><a href="#l578"></a>
<span id="l579">     /**</span><a href="#l579"></a>
<span id="l580">      * Returns a &quot;Generation Qualifier&quot; name component.  If more than one</span><a href="#l580"></a>
<span id="l581">      * such component exists, the topmost one is returned.</span><a href="#l581"></a>
<span id="l582">      *</span><a href="#l582"></a>
<span id="l583">      * @return &quot;GENERATION=&quot; component of the name, if any.</span><a href="#l583"></a>
<span id="l584">      */</span><a href="#l584"></a>
<span id="l585">    public String getGeneration() throws IOException {</span><a href="#l585"></a>
<span id="l586">        DerValue attr = findAttribute(GENERATIONQUALIFIER_OID);</span><a href="#l586"></a>
<span id="l587"></span><a href="#l587"></a>
<span id="l588">        return getString(attr);</span><a href="#l588"></a>
<span id="l589">    }</span><a href="#l589"></a>
<span id="l590"></span><a href="#l590"></a>
<span id="l591">    /**</span><a href="#l591"></a>
<span id="l592">     * Returns an &quot;IP address&quot; name component.  If more than one</span><a href="#l592"></a>
<span id="l593">     * such component exists, the topmost one is returned.</span><a href="#l593"></a>
<span id="l594">     *</span><a href="#l594"></a>
<span id="l595">     * @return &quot;IP=&quot; component of the name, if any.</span><a href="#l595"></a>
<span id="l596">     */</span><a href="#l596"></a>
<span id="l597">    public String getIP() throws IOException {</span><a href="#l597"></a>
<span id="l598">        DerValue attr = findAttribute(ipAddress_oid);</span><a href="#l598"></a>
<span id="l599"></span><a href="#l599"></a>
<span id="l600">        return getString(attr);</span><a href="#l600"></a>
<span id="l601">    }</span><a href="#l601"></a>
<span id="l602"></span><a href="#l602"></a>
<span id="l603">    /**</span><a href="#l603"></a>
<span id="l604">     * Returns a string form of the X.500 distinguished name.</span><a href="#l604"></a>
<span id="l605">     * The format of the string is from RFC 1779. The returned string</span><a href="#l605"></a>
<span id="l606">     * may contain non-standardised keywords for more readability</span><a href="#l606"></a>
<span id="l607">     * (keywords from RFCs 1779, 2253, and 5280).</span><a href="#l607"></a>
<span id="l608">     */</span><a href="#l608"></a>
<span id="l609">    public String toString() {</span><a href="#l609"></a>
<span id="l610">        if (dn == null) {</span><a href="#l610"></a>
<span id="l611">            generateDN();</span><a href="#l611"></a>
<span id="l612">        }</span><a href="#l612"></a>
<span id="l613">        return dn;</span><a href="#l613"></a>
<span id="l614">    }</span><a href="#l614"></a>
<span id="l615"></span><a href="#l615"></a>
<span id="l616">    /**</span><a href="#l616"></a>
<span id="l617">     * Returns a string form of the X.500 distinguished name</span><a href="#l617"></a>
<span id="l618">     * using the algorithm defined in RFC 1779. Only standard attribute type</span><a href="#l618"></a>
<span id="l619">     * keywords defined in RFC 1779 are emitted.</span><a href="#l619"></a>
<span id="l620">     */</span><a href="#l620"></a>
<span id="l621">    public String getRFC1779Name() {</span><a href="#l621"></a>
<span id="l622">        return getRFC1779Name(Collections.&lt;String, String&gt;emptyMap());</span><a href="#l622"></a>
<span id="l623">    }</span><a href="#l623"></a>
<span id="l624"></span><a href="#l624"></a>
<span id="l625">    /**</span><a href="#l625"></a>
<span id="l626">     * Returns a string form of the X.500 distinguished name</span><a href="#l626"></a>
<span id="l627">     * using the algorithm defined in RFC 1779. Attribute type</span><a href="#l627"></a>
<span id="l628">     * keywords defined in RFC 1779 are emitted, as well as additional</span><a href="#l628"></a>
<span id="l629">     * keywords contained in the OID/keyword map.</span><a href="#l629"></a>
<span id="l630">     */</span><a href="#l630"></a>
<span id="l631">    public String getRFC1779Name(Map&lt;String, String&gt; oidMap)</span><a href="#l631"></a>
<span id="l632">        throws IllegalArgumentException {</span><a href="#l632"></a>
<span id="l633">        if (oidMap.isEmpty()) {</span><a href="#l633"></a>
<span id="l634">            // return cached result</span><a href="#l634"></a>
<span id="l635">            if (rfc1779Dn != null) {</span><a href="#l635"></a>
<span id="l636">                return rfc1779Dn;</span><a href="#l636"></a>
<span id="l637">            } else {</span><a href="#l637"></a>
<span id="l638">                rfc1779Dn = generateRFC1779DN(oidMap);</span><a href="#l638"></a>
<span id="l639">                return rfc1779Dn;</span><a href="#l639"></a>
<span id="l640">            }</span><a href="#l640"></a>
<span id="l641">        }</span><a href="#l641"></a>
<span id="l642">        return generateRFC1779DN(oidMap);</span><a href="#l642"></a>
<span id="l643">    }</span><a href="#l643"></a>
<span id="l644"></span><a href="#l644"></a>
<span id="l645">    /**</span><a href="#l645"></a>
<span id="l646">     * Returns a string form of the X.500 distinguished name</span><a href="#l646"></a>
<span id="l647">     * using the algorithm defined in RFC 2253. Only standard attribute type</span><a href="#l647"></a>
<span id="l648">     * keywords defined in RFC 2253 are emitted.</span><a href="#l648"></a>
<span id="l649">     */</span><a href="#l649"></a>
<span id="l650">    public String getRFC2253Name() {</span><a href="#l650"></a>
<span id="l651">        return getRFC2253Name(Collections.&lt;String, String&gt;emptyMap());</span><a href="#l651"></a>
<span id="l652">    }</span><a href="#l652"></a>
<span id="l653"></span><a href="#l653"></a>
<span id="l654">    /**</span><a href="#l654"></a>
<span id="l655">     * Returns a string form of the X.500 distinguished name</span><a href="#l655"></a>
<span id="l656">     * using the algorithm defined in RFC 2253. Attribute type</span><a href="#l656"></a>
<span id="l657">     * keywords defined in RFC 2253 are emitted, as well as additional</span><a href="#l657"></a>
<span id="l658">     * keywords contained in the OID/keyword map.</span><a href="#l658"></a>
<span id="l659">     */</span><a href="#l659"></a>
<span id="l660">    public String getRFC2253Name(Map&lt;String, String&gt; oidMap) {</span><a href="#l660"></a>
<span id="l661">        /* check for and return cached name */</span><a href="#l661"></a>
<span id="l662">        if (oidMap.isEmpty()) {</span><a href="#l662"></a>
<span id="l663">            if (rfc2253Dn != null) {</span><a href="#l663"></a>
<span id="l664">                return rfc2253Dn;</span><a href="#l664"></a>
<span id="l665">            } else {</span><a href="#l665"></a>
<span id="l666">                rfc2253Dn = generateRFC2253DN(oidMap);</span><a href="#l666"></a>
<span id="l667">                return rfc2253Dn;</span><a href="#l667"></a>
<span id="l668">            }</span><a href="#l668"></a>
<span id="l669">        }</span><a href="#l669"></a>
<span id="l670">        return generateRFC2253DN(oidMap);</span><a href="#l670"></a>
<span id="l671">    }</span><a href="#l671"></a>
<span id="l672"></span><a href="#l672"></a>
<span id="l673">    private String generateRFC2253DN(Map&lt;String, String&gt; oidMap) {</span><a href="#l673"></a>
<span id="l674">        /*</span><a href="#l674"></a>
<span id="l675">         * Section 2.1 : if the RDNSequence is an empty sequence</span><a href="#l675"></a>
<span id="l676">         * the result is the empty or zero length string.</span><a href="#l676"></a>
<span id="l677">         */</span><a href="#l677"></a>
<span id="l678">        if (names.length == 0) {</span><a href="#l678"></a>
<span id="l679">            return &quot;&quot;;</span><a href="#l679"></a>
<span id="l680">        }</span><a href="#l680"></a>
<span id="l681"></span><a href="#l681"></a>
<span id="l682">        /*</span><a href="#l682"></a>
<span id="l683">         * 2.1 (continued) : Otherwise, the output consists of the string</span><a href="#l683"></a>
<span id="l684">         * encodings of each RelativeDistinguishedName in the RDNSequence</span><a href="#l684"></a>
<span id="l685">         * (according to 2.2), starting with the last element of the sequence</span><a href="#l685"></a>
<span id="l686">         * and moving backwards toward the first.</span><a href="#l686"></a>
<span id="l687">         *</span><a href="#l687"></a>
<span id="l688">         * The encodings of adjoining RelativeDistinguishedNames are separated</span><a href="#l688"></a>
<span id="l689">         * by a comma character (',' ASCII 44).</span><a href="#l689"></a>
<span id="l690">         */</span><a href="#l690"></a>
<span id="l691">        StringBuilder fullname = new StringBuilder(48);</span><a href="#l691"></a>
<span id="l692">        for (int i = names.length - 1; i &gt;= 0; i--) {</span><a href="#l692"></a>
<span id="l693">            if (i &lt; names.length - 1) {</span><a href="#l693"></a>
<span id="l694">                fullname.append(',');</span><a href="#l694"></a>
<span id="l695">            }</span><a href="#l695"></a>
<span id="l696">            fullname.append(names[i].toRFC2253String(oidMap));</span><a href="#l696"></a>
<span id="l697">        }</span><a href="#l697"></a>
<span id="l698">        return fullname.toString();</span><a href="#l698"></a>
<span id="l699">    }</span><a href="#l699"></a>
<span id="l700"></span><a href="#l700"></a>
<span id="l701">    public String getRFC2253CanonicalName() {</span><a href="#l701"></a>
<span id="l702">        /* check for and return cached name */</span><a href="#l702"></a>
<span id="l703">        if (canonicalDn != null) {</span><a href="#l703"></a>
<span id="l704">            return canonicalDn;</span><a href="#l704"></a>
<span id="l705">        }</span><a href="#l705"></a>
<span id="l706">        /*</span><a href="#l706"></a>
<span id="l707">         * Section 2.1 : if the RDNSequence is an empty sequence</span><a href="#l707"></a>
<span id="l708">         * the result is the empty or zero length string.</span><a href="#l708"></a>
<span id="l709">         */</span><a href="#l709"></a>
<span id="l710">        if (names.length == 0) {</span><a href="#l710"></a>
<span id="l711">            canonicalDn = &quot;&quot;;</span><a href="#l711"></a>
<span id="l712">            return canonicalDn;</span><a href="#l712"></a>
<span id="l713">        }</span><a href="#l713"></a>
<span id="l714"></span><a href="#l714"></a>
<span id="l715">        /*</span><a href="#l715"></a>
<span id="l716">         * 2.1 (continued) : Otherwise, the output consists of the string</span><a href="#l716"></a>
<span id="l717">         * encodings of each RelativeDistinguishedName in the RDNSequence</span><a href="#l717"></a>
<span id="l718">         * (according to 2.2), starting with the last element of the sequence</span><a href="#l718"></a>
<span id="l719">         * and moving backwards toward the first.</span><a href="#l719"></a>
<span id="l720">         *</span><a href="#l720"></a>
<span id="l721">         * The encodings of adjoining RelativeDistinguishedNames are separated</span><a href="#l721"></a>
<span id="l722">         * by a comma character (',' ASCII 44).</span><a href="#l722"></a>
<span id="l723">         */</span><a href="#l723"></a>
<span id="l724">        StringBuilder fullname = new StringBuilder(48);</span><a href="#l724"></a>
<span id="l725">        for (int i = names.length - 1; i &gt;= 0; i--) {</span><a href="#l725"></a>
<span id="l726">            if (i &lt; names.length - 1) {</span><a href="#l726"></a>
<span id="l727">                fullname.append(',');</span><a href="#l727"></a>
<span id="l728">            }</span><a href="#l728"></a>
<span id="l729">            fullname.append(names[i].toRFC2253String(true));</span><a href="#l729"></a>
<span id="l730">        }</span><a href="#l730"></a>
<span id="l731">        canonicalDn = fullname.toString();</span><a href="#l731"></a>
<span id="l732">        return canonicalDn;</span><a href="#l732"></a>
<span id="l733">    }</span><a href="#l733"></a>
<span id="l734"></span><a href="#l734"></a>
<span id="l735">    /**</span><a href="#l735"></a>
<span id="l736">     * Returns the value of toString().  This call is needed to</span><a href="#l736"></a>
<span id="l737">     * implement the java.security.Principal interface.</span><a href="#l737"></a>
<span id="l738">     */</span><a href="#l738"></a>
<span id="l739">    public String getName() { return toString(); }</span><a href="#l739"></a>
<span id="l740"></span><a href="#l740"></a>
<span id="l741">    /**</span><a href="#l741"></a>
<span id="l742">     * Find the first instance of this attribute in a &quot;top down&quot;</span><a href="#l742"></a>
<span id="l743">     * search of all the attributes in the name.</span><a href="#l743"></a>
<span id="l744">     */</span><a href="#l744"></a>
<span id="l745">    private DerValue findAttribute(ObjectIdentifier attribute) {</span><a href="#l745"></a>
<span id="l746">        if (names != null) {</span><a href="#l746"></a>
<span id="l747">            for (int i = 0; i &lt; names.length; i++) {</span><a href="#l747"></a>
<span id="l748">                DerValue value = names[i].findAttribute(attribute);</span><a href="#l748"></a>
<span id="l749">                if (value != null) {</span><a href="#l749"></a>
<span id="l750">                    return value;</span><a href="#l750"></a>
<span id="l751">                }</span><a href="#l751"></a>
<span id="l752">            }</span><a href="#l752"></a>
<span id="l753">        }</span><a href="#l753"></a>
<span id="l754">        return null;</span><a href="#l754"></a>
<span id="l755">    }</span><a href="#l755"></a>
<span id="l756"></span><a href="#l756"></a>
<span id="l757">    /**</span><a href="#l757"></a>
<span id="l758">     * Find the most specific (&quot;last&quot;) attribute of the given</span><a href="#l758"></a>
<span id="l759">     * type.</span><a href="#l759"></a>
<span id="l760">     */</span><a href="#l760"></a>
<span id="l761">    public DerValue findMostSpecificAttribute(ObjectIdentifier attribute) {</span><a href="#l761"></a>
<span id="l762">        if (names != null) {</span><a href="#l762"></a>
<span id="l763">            for (int i = names.length - 1; i &gt;= 0; i--) {</span><a href="#l763"></a>
<span id="l764">                DerValue value = names[i].findAttribute(attribute);</span><a href="#l764"></a>
<span id="l765">                if (value != null) {</span><a href="#l765"></a>
<span id="l766">                    return value;</span><a href="#l766"></a>
<span id="l767">                }</span><a href="#l767"></a>
<span id="l768">            }</span><a href="#l768"></a>
<span id="l769">        }</span><a href="#l769"></a>
<span id="l770">        return null;</span><a href="#l770"></a>
<span id="l771">    }</span><a href="#l771"></a>
<span id="l772"></span><a href="#l772"></a>
<span id="l773">    /****************************************************************/</span><a href="#l773"></a>
<span id="l774"></span><a href="#l774"></a>
<span id="l775">    private void parseDER(DerInputStream in) throws IOException {</span><a href="#l775"></a>
<span id="l776">        //</span><a href="#l776"></a>
<span id="l777">        // X.500 names are a &quot;SEQUENCE OF&quot; RDNs, which means zero or</span><a href="#l777"></a>
<span id="l778">        // more and order matters.  We scan them in order, which</span><a href="#l778"></a>
<span id="l779">        // conventionally is big-endian.</span><a href="#l779"></a>
<span id="l780">        //</span><a href="#l780"></a>
<span id="l781">        DerValue[] nameseq = null;</span><a href="#l781"></a>
<span id="l782">        byte[] derBytes = in.toByteArray();</span><a href="#l782"></a>
<span id="l783"></span><a href="#l783"></a>
<span id="l784">        try {</span><a href="#l784"></a>
<span id="l785">            nameseq = in.getSequence(5);</span><a href="#l785"></a>
<span id="l786">        } catch (IOException ioe) {</span><a href="#l786"></a>
<span id="l787">            if (derBytes == null) {</span><a href="#l787"></a>
<span id="l788">                nameseq = null;</span><a href="#l788"></a>
<span id="l789">            } else {</span><a href="#l789"></a>
<span id="l790">                DerValue derVal = new DerValue(DerValue.tag_Sequence,</span><a href="#l790"></a>
<span id="l791">                                           derBytes);</span><a href="#l791"></a>
<span id="l792">                derBytes = derVal.toByteArray();</span><a href="#l792"></a>
<span id="l793">                nameseq = new DerInputStream(derBytes).getSequence(5);</span><a href="#l793"></a>
<span id="l794">            }</span><a href="#l794"></a>
<span id="l795">        }</span><a href="#l795"></a>
<span id="l796"></span><a href="#l796"></a>
<span id="l797">        if (nameseq == null) {</span><a href="#l797"></a>
<span id="l798">            names = new RDN[0];</span><a href="#l798"></a>
<span id="l799">        } else {</span><a href="#l799"></a>
<span id="l800">            names = new RDN[nameseq.length];</span><a href="#l800"></a>
<span id="l801">            for (int i = 0; i &lt; nameseq.length; i++) {</span><a href="#l801"></a>
<span id="l802">                names[i] = new RDN(nameseq[i]);</span><a href="#l802"></a>
<span id="l803">            }</span><a href="#l803"></a>
<span id="l804">        }</span><a href="#l804"></a>
<span id="l805">    }</span><a href="#l805"></a>
<span id="l806"></span><a href="#l806"></a>
<span id="l807">    /**</span><a href="#l807"></a>
<span id="l808">     * Encodes the name in DER-encoded form.</span><a href="#l808"></a>
<span id="l809">     *</span><a href="#l809"></a>
<span id="l810">     * @deprecated Use encode() instead</span><a href="#l810"></a>
<span id="l811">     * @param out where to put the DER-encoded X.500 name</span><a href="#l811"></a>
<span id="l812">     */</span><a href="#l812"></a>
<span id="l813">    @Deprecated</span><a href="#l813"></a>
<span id="l814">    public void emit(DerOutputStream out) throws IOException {</span><a href="#l814"></a>
<span id="l815">        encode(out);</span><a href="#l815"></a>
<span id="l816">    }</span><a href="#l816"></a>
<span id="l817"></span><a href="#l817"></a>
<span id="l818">    /**</span><a href="#l818"></a>
<span id="l819">     * Encodes the name in DER-encoded form.</span><a href="#l819"></a>
<span id="l820">     *</span><a href="#l820"></a>
<span id="l821">     * @param out where to put the DER-encoded X.500 name</span><a href="#l821"></a>
<span id="l822">     */</span><a href="#l822"></a>
<span id="l823">    public void encode(DerOutputStream out) throws IOException {</span><a href="#l823"></a>
<span id="l824">        DerOutputStream tmp = new DerOutputStream();</span><a href="#l824"></a>
<span id="l825">        for (int i = 0; i &lt; names.length; i++) {</span><a href="#l825"></a>
<span id="l826">            names[i].encode(tmp);</span><a href="#l826"></a>
<span id="l827">        }</span><a href="#l827"></a>
<span id="l828">        out.write(DerValue.tag_Sequence, tmp);</span><a href="#l828"></a>
<span id="l829">    }</span><a href="#l829"></a>
<span id="l830"></span><a href="#l830"></a>
<span id="l831">    /**</span><a href="#l831"></a>
<span id="l832">     * Returned the encoding as an uncloned byte array. Callers must</span><a href="#l832"></a>
<span id="l833">     * guarantee that they neither modify it not expose it to untrusted</span><a href="#l833"></a>
<span id="l834">     * code.</span><a href="#l834"></a>
<span id="l835">     */</span><a href="#l835"></a>
<span id="l836">    public byte[] getEncodedInternal() throws IOException {</span><a href="#l836"></a>
<span id="l837">        if (encoded == null) {</span><a href="#l837"></a>
<span id="l838">            DerOutputStream     out = new DerOutputStream();</span><a href="#l838"></a>
<span id="l839">            DerOutputStream     tmp = new DerOutputStream();</span><a href="#l839"></a>
<span id="l840">            for (int i = 0; i &lt; names.length; i++) {</span><a href="#l840"></a>
<span id="l841">                names[i].encode(tmp);</span><a href="#l841"></a>
<span id="l842">            }</span><a href="#l842"></a>
<span id="l843">            out.write(DerValue.tag_Sequence, tmp);</span><a href="#l843"></a>
<span id="l844">            encoded = out.toByteArray();</span><a href="#l844"></a>
<span id="l845">        }</span><a href="#l845"></a>
<span id="l846">        return encoded;</span><a href="#l846"></a>
<span id="l847">    }</span><a href="#l847"></a>
<span id="l848"></span><a href="#l848"></a>
<span id="l849">    /**</span><a href="#l849"></a>
<span id="l850">     * Gets the name in DER-encoded form.</span><a href="#l850"></a>
<span id="l851">     *</span><a href="#l851"></a>
<span id="l852">     * @return the DER encoded byte array of this name.</span><a href="#l852"></a>
<span id="l853">     */</span><a href="#l853"></a>
<span id="l854">    public byte[] getEncoded() throws IOException {</span><a href="#l854"></a>
<span id="l855">        return getEncodedInternal().clone();</span><a href="#l855"></a>
<span id="l856">    }</span><a href="#l856"></a>
<span id="l857"></span><a href="#l857"></a>
<span id="l858">    /*</span><a href="#l858"></a>
<span id="l859">     * Parses a Distinguished Name (DN) in printable representation.</span><a href="#l859"></a>
<span id="l860">     *</span><a href="#l860"></a>
<span id="l861">     * According to RFC 1779, RDNs in a DN are separated by comma.</span><a href="#l861"></a>
<span id="l862">     * The following examples show both methods of quoting a comma, so that it</span><a href="#l862"></a>
<span id="l863">     * is not considered a separator:</span><a href="#l863"></a>
<span id="l864">     *</span><a href="#l864"></a>
<span id="l865">     *     O=&quot;Sue, Grabbit and Runn&quot; or</span><a href="#l865"></a>
<span id="l866">     *     O=Sue\, Grabbit and Runn</span><a href="#l866"></a>
<span id="l867">     *</span><a href="#l867"></a>
<span id="l868">     * This method can parse RFC 1779, 2253 or 4514 DNs and non-standard 5280</span><a href="#l868"></a>
<span id="l869">     * keywords. Additional keywords can be specified in the keyword/OID map.</span><a href="#l869"></a>
<span id="l870">     */</span><a href="#l870"></a>
<span id="l871">    private void parseDN(String input, Map&lt;String, String&gt; keywordMap)</span><a href="#l871"></a>
<span id="l872">        throws IOException {</span><a href="#l872"></a>
<span id="l873">        if (input == null || input.length() == 0) {</span><a href="#l873"></a>
<span id="l874">            names = new RDN[0];</span><a href="#l874"></a>
<span id="l875">            return;</span><a href="#l875"></a>
<span id="l876">        }</span><a href="#l876"></a>
<span id="l877"></span><a href="#l877"></a>
<span id="l878">        List&lt;RDN&gt; dnVector = new ArrayList&lt;&gt;();</span><a href="#l878"></a>
<span id="l879">        int dnOffset = 0;</span><a href="#l879"></a>
<span id="l880">        int rdnEnd;</span><a href="#l880"></a>
<span id="l881">        String rdnString;</span><a href="#l881"></a>
<span id="l882">        int quoteCount = 0;</span><a href="#l882"></a>
<span id="l883"></span><a href="#l883"></a>
<span id="l884">        String dnString = input;</span><a href="#l884"></a>
<span id="l885"></span><a href="#l885"></a>
<span id="l886">        int searchOffset = 0;</span><a href="#l886"></a>
<span id="l887">        int nextComma = dnString.indexOf(',');</span><a href="#l887"></a>
<span id="l888">        int nextSemiColon = dnString.indexOf(';');</span><a href="#l888"></a>
<span id="l889">        while (nextComma &gt;=0 || nextSemiColon &gt;=0) {</span><a href="#l889"></a>
<span id="l890"></span><a href="#l890"></a>
<span id="l891">            if (nextSemiColon &lt; 0) {</span><a href="#l891"></a>
<span id="l892">                rdnEnd = nextComma;</span><a href="#l892"></a>
<span id="l893">            } else if (nextComma &lt; 0) {</span><a href="#l893"></a>
<span id="l894">                rdnEnd = nextSemiColon;</span><a href="#l894"></a>
<span id="l895">            } else {</span><a href="#l895"></a>
<span id="l896">                rdnEnd = Math.min(nextComma, nextSemiColon);</span><a href="#l896"></a>
<span id="l897">            }</span><a href="#l897"></a>
<span id="l898">            quoteCount += countQuotes(dnString, searchOffset, rdnEnd);</span><a href="#l898"></a>
<span id="l899"></span><a href="#l899"></a>
<span id="l900">            /*</span><a href="#l900"></a>
<span id="l901">             * We have encountered an RDN delimiter (comma or a semicolon).</span><a href="#l901"></a>
<span id="l902">             * If the comma or semicolon in the RDN under consideration is</span><a href="#l902"></a>
<span id="l903">             * preceded by a backslash (escape), or by a double quote, it</span><a href="#l903"></a>
<span id="l904">             * is part of the RDN. Otherwise, it is used as a separator, to</span><a href="#l904"></a>
<span id="l905">             * delimit the RDN under consideration from any subsequent RDNs.</span><a href="#l905"></a>
<span id="l906">             */</span><a href="#l906"></a>
<span id="l907">            if (rdnEnd &gt;= 0 &amp;&amp; quoteCount != 1 &amp;&amp;</span><a href="#l907"></a>
<span id="l908">                !escaped(rdnEnd, searchOffset, dnString)) {</span><a href="#l908"></a>
<span id="l909"></span><a href="#l909"></a>
<span id="l910">                /*</span><a href="#l910"></a>
<span id="l911">                 * Comma/semicolon is a separator</span><a href="#l911"></a>
<span id="l912">                 */</span><a href="#l912"></a>
<span id="l913">                rdnString = dnString.substring(dnOffset, rdnEnd);</span><a href="#l913"></a>
<span id="l914"></span><a href="#l914"></a>
<span id="l915">                // Parse RDN, and store it in vector</span><a href="#l915"></a>
<span id="l916">                RDN rdn = new RDN(rdnString, keywordMap);</span><a href="#l916"></a>
<span id="l917">                dnVector.add(rdn);</span><a href="#l917"></a>
<span id="l918"></span><a href="#l918"></a>
<span id="l919">                // Increase the offset</span><a href="#l919"></a>
<span id="l920">                dnOffset = rdnEnd + 1;</span><a href="#l920"></a>
<span id="l921"></span><a href="#l921"></a>
<span id="l922">                // Set quote counter back to zero</span><a href="#l922"></a>
<span id="l923">                quoteCount = 0;</span><a href="#l923"></a>
<span id="l924">            }</span><a href="#l924"></a>
<span id="l925"></span><a href="#l925"></a>
<span id="l926">            searchOffset = rdnEnd + 1;</span><a href="#l926"></a>
<span id="l927">            nextComma = dnString.indexOf(',', searchOffset);</span><a href="#l927"></a>
<span id="l928">            nextSemiColon = dnString.indexOf(';', searchOffset);</span><a href="#l928"></a>
<span id="l929">        }</span><a href="#l929"></a>
<span id="l930"></span><a href="#l930"></a>
<span id="l931">        // Parse last or only RDN, and store it in vector</span><a href="#l931"></a>
<span id="l932">        rdnString = dnString.substring(dnOffset);</span><a href="#l932"></a>
<span id="l933">        RDN rdn = new RDN(rdnString, keywordMap);</span><a href="#l933"></a>
<span id="l934">        dnVector.add(rdn);</span><a href="#l934"></a>
<span id="l935"></span><a href="#l935"></a>
<span id="l936">        /*</span><a href="#l936"></a>
<span id="l937">         * Store the vector elements as an array of RDNs</span><a href="#l937"></a>
<span id="l938">         * NOTE: It's only on output that little-endian ordering is used.</span><a href="#l938"></a>
<span id="l939">         */</span><a href="#l939"></a>
<span id="l940">        Collections.reverse(dnVector);</span><a href="#l940"></a>
<span id="l941">        names = dnVector.toArray(new RDN[dnVector.size()]);</span><a href="#l941"></a>
<span id="l942">    }</span><a href="#l942"></a>
<span id="l943"></span><a href="#l943"></a>
<span id="l944">    private void parseRFC2253DN(String dnString) throws IOException {</span><a href="#l944"></a>
<span id="l945">        if (dnString.length() == 0) {</span><a href="#l945"></a>
<span id="l946">            names = new RDN[0];</span><a href="#l946"></a>
<span id="l947">            return;</span><a href="#l947"></a>
<span id="l948">         }</span><a href="#l948"></a>
<span id="l949"></span><a href="#l949"></a>
<span id="l950">         List&lt;RDN&gt; dnVector = new ArrayList&lt;&gt;();</span><a href="#l950"></a>
<span id="l951">         int dnOffset = 0;</span><a href="#l951"></a>
<span id="l952">         String rdnString;</span><a href="#l952"></a>
<span id="l953">         int searchOffset = 0;</span><a href="#l953"></a>
<span id="l954">         int rdnEnd = dnString.indexOf(',');</span><a href="#l954"></a>
<span id="l955">         while (rdnEnd &gt;=0) {</span><a href="#l955"></a>
<span id="l956">             /*</span><a href="#l956"></a>
<span id="l957">              * We have encountered an RDN delimiter (comma).</span><a href="#l957"></a>
<span id="l958">              * If the comma in the RDN under consideration is</span><a href="#l958"></a>
<span id="l959">              * preceded by a backslash (escape), it</span><a href="#l959"></a>
<span id="l960">              * is part of the RDN. Otherwise, it is used as a separator, to</span><a href="#l960"></a>
<span id="l961">              * delimit the RDN under consideration from any subsequent RDNs.</span><a href="#l961"></a>
<span id="l962">              */</span><a href="#l962"></a>
<span id="l963">             if (rdnEnd &gt; 0 &amp;&amp; !escaped(rdnEnd, searchOffset, dnString)) {</span><a href="#l963"></a>
<span id="l964"></span><a href="#l964"></a>
<span id="l965">                 /*</span><a href="#l965"></a>
<span id="l966">                  * Comma is a separator</span><a href="#l966"></a>
<span id="l967">                  */</span><a href="#l967"></a>
<span id="l968">                 rdnString = dnString.substring(dnOffset, rdnEnd);</span><a href="#l968"></a>
<span id="l969"></span><a href="#l969"></a>
<span id="l970">                 // Parse RDN, and store it in vector</span><a href="#l970"></a>
<span id="l971">                 RDN rdn = new RDN(rdnString, &quot;RFC2253&quot;);</span><a href="#l971"></a>
<span id="l972">                 dnVector.add(rdn);</span><a href="#l972"></a>
<span id="l973"></span><a href="#l973"></a>
<span id="l974">                 // Increase the offset</span><a href="#l974"></a>
<span id="l975">                 dnOffset = rdnEnd + 1;</span><a href="#l975"></a>
<span id="l976">             }</span><a href="#l976"></a>
<span id="l977"></span><a href="#l977"></a>
<span id="l978">             searchOffset = rdnEnd + 1;</span><a href="#l978"></a>
<span id="l979">             rdnEnd = dnString.indexOf(',', searchOffset);</span><a href="#l979"></a>
<span id="l980">         }</span><a href="#l980"></a>
<span id="l981"></span><a href="#l981"></a>
<span id="l982">         // Parse last or only RDN, and store it in vector</span><a href="#l982"></a>
<span id="l983">         rdnString = dnString.substring(dnOffset);</span><a href="#l983"></a>
<span id="l984">         RDN rdn = new RDN(rdnString, &quot;RFC2253&quot;);</span><a href="#l984"></a>
<span id="l985">         dnVector.add(rdn);</span><a href="#l985"></a>
<span id="l986"></span><a href="#l986"></a>
<span id="l987">         /*</span><a href="#l987"></a>
<span id="l988">          * Store the vector elements as an array of RDNs</span><a href="#l988"></a>
<span id="l989">          * NOTE: It's only on output that little-endian ordering is used.</span><a href="#l989"></a>
<span id="l990">          */</span><a href="#l990"></a>
<span id="l991">         Collections.reverse(dnVector);</span><a href="#l991"></a>
<span id="l992">         names = dnVector.toArray(new RDN[dnVector.size()]);</span><a href="#l992"></a>
<span id="l993">    }</span><a href="#l993"></a>
<span id="l994"></span><a href="#l994"></a>
<span id="l995">    /*</span><a href="#l995"></a>
<span id="l996">     * Counts double quotes in string.</span><a href="#l996"></a>
<span id="l997">     * Escaped quotes are ignored.</span><a href="#l997"></a>
<span id="l998">     */</span><a href="#l998"></a>
<span id="l999">    static int countQuotes(String string, int from, int to) {</span><a href="#l999"></a>
<span id="l1000">        int count = 0;</span><a href="#l1000"></a>
<span id="l1001"></span><a href="#l1001"></a>
<span id="l1002">        for (int i = from; i &lt; to; i++) {</span><a href="#l1002"></a>
<span id="l1003">            if ((string.charAt(i) == '&quot;' &amp;&amp; i == from) ||</span><a href="#l1003"></a>
<span id="l1004">                (string.charAt(i) == '&quot;' &amp;&amp; string.charAt(i-1) != '\\')) {</span><a href="#l1004"></a>
<span id="l1005">                count++;</span><a href="#l1005"></a>
<span id="l1006">            }</span><a href="#l1006"></a>
<span id="l1007">        }</span><a href="#l1007"></a>
<span id="l1008"></span><a href="#l1008"></a>
<span id="l1009">        return count;</span><a href="#l1009"></a>
<span id="l1010">    }</span><a href="#l1010"></a>
<span id="l1011"></span><a href="#l1011"></a>
<span id="l1012">    private static boolean escaped</span><a href="#l1012"></a>
<span id="l1013">                (int rdnEnd, int searchOffset, String dnString) {</span><a href="#l1013"></a>
<span id="l1014"></span><a href="#l1014"></a>
<span id="l1015">        if (rdnEnd == 1 &amp;&amp; dnString.charAt(rdnEnd - 1) == '\\') {</span><a href="#l1015"></a>
<span id="l1016"></span><a href="#l1016"></a>
<span id="l1017">            //  case 1:</span><a href="#l1017"></a>
<span id="l1018">            //  \,</span><a href="#l1018"></a>
<span id="l1019"></span><a href="#l1019"></a>
<span id="l1020">            return true;</span><a href="#l1020"></a>
<span id="l1021"></span><a href="#l1021"></a>
<span id="l1022">        } else if (rdnEnd &gt; 1 &amp;&amp; dnString.charAt(rdnEnd - 1) == '\\' &amp;&amp;</span><a href="#l1022"></a>
<span id="l1023">                dnString.charAt(rdnEnd - 2) != '\\') {</span><a href="#l1023"></a>
<span id="l1024"></span><a href="#l1024"></a>
<span id="l1025">            //  case 2:</span><a href="#l1025"></a>
<span id="l1026">            //  foo\,</span><a href="#l1026"></a>
<span id="l1027"></span><a href="#l1027"></a>
<span id="l1028">            return true;</span><a href="#l1028"></a>
<span id="l1029"></span><a href="#l1029"></a>
<span id="l1030">        } else if (rdnEnd &gt; 1 &amp;&amp; dnString.charAt(rdnEnd - 1) == '\\' &amp;&amp;</span><a href="#l1030"></a>
<span id="l1031">                dnString.charAt(rdnEnd - 2) == '\\') {</span><a href="#l1031"></a>
<span id="l1032"></span><a href="#l1032"></a>
<span id="l1033">            //  case 3:</span><a href="#l1033"></a>
<span id="l1034">            //  foo\\\\\,</span><a href="#l1034"></a>
<span id="l1035"></span><a href="#l1035"></a>
<span id="l1036">            int count = 0;</span><a href="#l1036"></a>
<span id="l1037">            rdnEnd--;   // back up to last backSlash</span><a href="#l1037"></a>
<span id="l1038">            while (rdnEnd &gt;= searchOffset) {</span><a href="#l1038"></a>
<span id="l1039">                if (dnString.charAt(rdnEnd) == '\\') {</span><a href="#l1039"></a>
<span id="l1040">                    count++;    // count consecutive backslashes</span><a href="#l1040"></a>
<span id="l1041">                }</span><a href="#l1041"></a>
<span id="l1042">                rdnEnd--;</span><a href="#l1042"></a>
<span id="l1043">            }</span><a href="#l1043"></a>
<span id="l1044"></span><a href="#l1044"></a>
<span id="l1045">            // if count is odd, then rdnEnd is escaped</span><a href="#l1045"></a>
<span id="l1046">            return (count % 2) != 0 ? true : false;</span><a href="#l1046"></a>
<span id="l1047"></span><a href="#l1047"></a>
<span id="l1048">        } else {</span><a href="#l1048"></a>
<span id="l1049">            return false;</span><a href="#l1049"></a>
<span id="l1050">        }</span><a href="#l1050"></a>
<span id="l1051">    }</span><a href="#l1051"></a>
<span id="l1052"></span><a href="#l1052"></a>
<span id="l1053">    /*</span><a href="#l1053"></a>
<span id="l1054">     * Dump the printable form of a distinguished name.  Each relative</span><a href="#l1054"></a>
<span id="l1055">     * name is separated from the next by a &quot;,&quot;, and assertions in the</span><a href="#l1055"></a>
<span id="l1056">     * relative names have &quot;label=value&quot; syntax.</span><a href="#l1056"></a>
<span id="l1057">     *</span><a href="#l1057"></a>
<span id="l1058">     * Uses RFC 1779 syntax (i.e. little-endian, comma separators)</span><a href="#l1058"></a>
<span id="l1059">     */</span><a href="#l1059"></a>
<span id="l1060">    private void generateDN() {</span><a href="#l1060"></a>
<span id="l1061">        if (names.length == 1) {</span><a href="#l1061"></a>
<span id="l1062">            dn = names[0].toString();</span><a href="#l1062"></a>
<span id="l1063">            return;</span><a href="#l1063"></a>
<span id="l1064">        }</span><a href="#l1064"></a>
<span id="l1065"></span><a href="#l1065"></a>
<span id="l1066">        StringBuilder sb = new StringBuilder(48);</span><a href="#l1066"></a>
<span id="l1067">        if (names != null) {</span><a href="#l1067"></a>
<span id="l1068">            for (int i = names.length - 1; i &gt;= 0; i--) {</span><a href="#l1068"></a>
<span id="l1069">                if (i != names.length - 1) {</span><a href="#l1069"></a>
<span id="l1070">                    sb.append(&quot;, &quot;);</span><a href="#l1070"></a>
<span id="l1071">                }</span><a href="#l1071"></a>
<span id="l1072">                sb.append(names[i].toString());</span><a href="#l1072"></a>
<span id="l1073">            }</span><a href="#l1073"></a>
<span id="l1074">        }</span><a href="#l1074"></a>
<span id="l1075">        dn = sb.toString();</span><a href="#l1075"></a>
<span id="l1076">    }</span><a href="#l1076"></a>
<span id="l1077"></span><a href="#l1077"></a>
<span id="l1078">    /*</span><a href="#l1078"></a>
<span id="l1079">     * Dump the printable form of a distinguished name.  Each relative</span><a href="#l1079"></a>
<span id="l1080">     * name is separated from the next by a &quot;,&quot;, and assertions in the</span><a href="#l1080"></a>
<span id="l1081">     * relative names have &quot;label=value&quot; syntax.</span><a href="#l1081"></a>
<span id="l1082">     *</span><a href="#l1082"></a>
<span id="l1083">     * Uses RFC 1779 syntax (i.e. little-endian, comma separators)</span><a href="#l1083"></a>
<span id="l1084">     * Valid keywords from RFC 1779 are used. Additional keywords can be</span><a href="#l1084"></a>
<span id="l1085">     * specified in the OID/keyword map.</span><a href="#l1085"></a>
<span id="l1086">     */</span><a href="#l1086"></a>
<span id="l1087">    private String generateRFC1779DN(Map&lt;String, String&gt; oidMap) {</span><a href="#l1087"></a>
<span id="l1088">        if (names.length == 1) {</span><a href="#l1088"></a>
<span id="l1089">            return names[0].toRFC1779String(oidMap);</span><a href="#l1089"></a>
<span id="l1090">        }</span><a href="#l1090"></a>
<span id="l1091"></span><a href="#l1091"></a>
<span id="l1092">        StringBuilder sb = new StringBuilder(48);</span><a href="#l1092"></a>
<span id="l1093">        if (names != null) {</span><a href="#l1093"></a>
<span id="l1094">            for (int i = names.length - 1; i &gt;= 0; i--) {</span><a href="#l1094"></a>
<span id="l1095">                if (i != names.length - 1) {</span><a href="#l1095"></a>
<span id="l1096">                    sb.append(&quot;, &quot;);</span><a href="#l1096"></a>
<span id="l1097">                }</span><a href="#l1097"></a>
<span id="l1098">                sb.append(names[i].toRFC1779String(oidMap));</span><a href="#l1098"></a>
<span id="l1099">            }</span><a href="#l1099"></a>
<span id="l1100">        }</span><a href="#l1100"></a>
<span id="l1101">        return sb.toString();</span><a href="#l1101"></a>
<span id="l1102">    }</span><a href="#l1102"></a>
<span id="l1103"></span><a href="#l1103"></a>
<span id="l1104">    /****************************************************************/</span><a href="#l1104"></a>
<span id="l1105"></span><a href="#l1105"></a>
<span id="l1106">    /*</span><a href="#l1106"></a>
<span id="l1107">     * Selected OIDs from X.520</span><a href="#l1107"></a>
<span id="l1108">     * Includes all those specified in RFC 5280 as MUST or SHOULD</span><a href="#l1108"></a>
<span id="l1109">     * be recognized</span><a href="#l1109"></a>
<span id="l1110">     */</span><a href="#l1110"></a>
<span id="l1111">    private static final int commonName_data[] = { 2, 5, 4, 3 };</span><a href="#l1111"></a>
<span id="l1112">    private static final int SURNAME_DATA[] = { 2, 5, 4, 4 };</span><a href="#l1112"></a>
<span id="l1113">    private static final int SERIALNUMBER_DATA[] = { 2, 5, 4, 5 };</span><a href="#l1113"></a>
<span id="l1114">    private static final int countryName_data[] = { 2, 5, 4, 6 };</span><a href="#l1114"></a>
<span id="l1115">    private static final int localityName_data[] = { 2, 5, 4, 7 };</span><a href="#l1115"></a>
<span id="l1116">    private static final int stateName_data[] = { 2, 5, 4, 8 };</span><a href="#l1116"></a>
<span id="l1117">    private static final int streetAddress_data[] = { 2, 5, 4, 9 };</span><a href="#l1117"></a>
<span id="l1118">    private static final int orgName_data[] = { 2, 5, 4, 10 };</span><a href="#l1118"></a>
<span id="l1119">    private static final int orgUnitName_data[] = { 2, 5, 4, 11 };</span><a href="#l1119"></a>
<span id="l1120">    private static final int title_data[] = { 2, 5, 4, 12 };</span><a href="#l1120"></a>
<span id="l1121">    private static final int GIVENNAME_DATA[] = { 2, 5, 4, 42 };</span><a href="#l1121"></a>
<span id="l1122">    private static final int INITIALS_DATA[] = { 2, 5, 4, 43 };</span><a href="#l1122"></a>
<span id="l1123">    private static final int GENERATIONQUALIFIER_DATA[] = { 2, 5, 4, 44 };</span><a href="#l1123"></a>
<span id="l1124">    private static final int DNQUALIFIER_DATA[] = { 2, 5, 4, 46 };</span><a href="#l1124"></a>
<span id="l1125"></span><a href="#l1125"></a>
<span id="l1126">    private static final int ipAddress_data[] = { 1, 3, 6, 1, 4, 1, 42, 2, 11, 2, 1 };</span><a href="#l1126"></a>
<span id="l1127">    private static final int DOMAIN_COMPONENT_DATA[] =</span><a href="#l1127"></a>
<span id="l1128">        { 0, 9, 2342, 19200300, 100, 1, 25 };</span><a href="#l1128"></a>
<span id="l1129">    private static final int userid_data[] =</span><a href="#l1129"></a>
<span id="l1130">        { 0, 9, 2342, 19200300, 100, 1, 1 };</span><a href="#l1130"></a>
<span id="l1131"></span><a href="#l1131"></a>
<span id="l1132"></span><a href="#l1132"></a>
<span id="l1133">    // OID for the &quot;CN=&quot; attribute, denoting a person's common name.</span><a href="#l1133"></a>
<span id="l1134">    public static final ObjectIdentifier commonName_oid =</span><a href="#l1134"></a>
<span id="l1135">            ObjectIdentifier.newInternal(commonName_data);</span><a href="#l1135"></a>
<span id="l1136"></span><a href="#l1136"></a>
<span id="l1137">    // OID for the &quot;SERIALNUMBER=&quot; attribute, denoting a serial number for.</span><a href="#l1137"></a>
<span id="l1138">    // a name. Do not confuse with PKCS#9 issuerAndSerialNumber or the</span><a href="#l1138"></a>
<span id="l1139">    // certificate serial number.</span><a href="#l1139"></a>
<span id="l1140">    public static final ObjectIdentifier SERIALNUMBER_OID =</span><a href="#l1140"></a>
<span id="l1141">            ObjectIdentifier.newInternal(SERIALNUMBER_DATA);</span><a href="#l1141"></a>
<span id="l1142"></span><a href="#l1142"></a>
<span id="l1143">    // OID for the &quot;C=&quot; attribute, denoting a country.</span><a href="#l1143"></a>
<span id="l1144">    public static final ObjectIdentifier countryName_oid =</span><a href="#l1144"></a>
<span id="l1145">            ObjectIdentifier.newInternal(countryName_data);</span><a href="#l1145"></a>
<span id="l1146"></span><a href="#l1146"></a>
<span id="l1147">    // OID for the &quot;L=&quot; attribute, denoting a locality (such as a city).</span><a href="#l1147"></a>
<span id="l1148">    public static final ObjectIdentifier localityName_oid =</span><a href="#l1148"></a>
<span id="l1149">            ObjectIdentifier.newInternal(localityName_data);</span><a href="#l1149"></a>
<span id="l1150"></span><a href="#l1150"></a>
<span id="l1151">    // OID for the &quot;O=&quot; attribute, denoting an organization name.</span><a href="#l1151"></a>
<span id="l1152">    public static final ObjectIdentifier orgName_oid =</span><a href="#l1152"></a>
<span id="l1153">            ObjectIdentifier.newInternal(orgName_data);</span><a href="#l1153"></a>
<span id="l1154"></span><a href="#l1154"></a>
<span id="l1155">    // OID for the &quot;OU=&quot; attribute, denoting an organizational unit name.</span><a href="#l1155"></a>
<span id="l1156">    public static final ObjectIdentifier orgUnitName_oid =</span><a href="#l1156"></a>
<span id="l1157">            ObjectIdentifier.newInternal(orgUnitName_data);</span><a href="#l1157"></a>
<span id="l1158"></span><a href="#l1158"></a>
<span id="l1159">    // OID for the &quot;S=&quot; attribute, denoting a state (such as Delaware).</span><a href="#l1159"></a>
<span id="l1160">    public static final ObjectIdentifier stateName_oid =</span><a href="#l1160"></a>
<span id="l1161">            ObjectIdentifier.newInternal(stateName_data);</span><a href="#l1161"></a>
<span id="l1162"></span><a href="#l1162"></a>
<span id="l1163">    // OID for the &quot;STREET=&quot; attribute, denoting a street address.</span><a href="#l1163"></a>
<span id="l1164">    public static final ObjectIdentifier streetAddress_oid =</span><a href="#l1164"></a>
<span id="l1165">            ObjectIdentifier.newInternal(streetAddress_data);</span><a href="#l1165"></a>
<span id="l1166"></span><a href="#l1166"></a>
<span id="l1167">    // OID for the &quot;T=&quot; attribute, denoting a person's title.</span><a href="#l1167"></a>
<span id="l1168">    public static final ObjectIdentifier title_oid =</span><a href="#l1168"></a>
<span id="l1169">            ObjectIdentifier.newInternal(title_data);</span><a href="#l1169"></a>
<span id="l1170"></span><a href="#l1170"></a>
<span id="l1171">    // OID for the &quot;DNQUALIFIER=&quot; or &quot;DNQ=&quot; attribute, denoting DN</span><a href="#l1171"></a>
<span id="l1172">    // disambiguating information.</span><a href="#l1172"></a>
<span id="l1173">    public static final ObjectIdentifier DNQUALIFIER_OID =</span><a href="#l1173"></a>
<span id="l1174">            ObjectIdentifier.newInternal(DNQUALIFIER_DATA);</span><a href="#l1174"></a>
<span id="l1175"></span><a href="#l1175"></a>
<span id="l1176">    // OID for the &quot;SURNAME=&quot; attribute, denoting a person's surname.</span><a href="#l1176"></a>
<span id="l1177">    public static final ObjectIdentifier SURNAME_OID =</span><a href="#l1177"></a>
<span id="l1178">            ObjectIdentifier.newInternal(SURNAME_DATA);</span><a href="#l1178"></a>
<span id="l1179"></span><a href="#l1179"></a>
<span id="l1180">    // OID for the &quot;GIVENNAME=&quot; attribute, denoting a person's given name.</span><a href="#l1180"></a>
<span id="l1181">    public static final ObjectIdentifier GIVENNAME_OID =</span><a href="#l1181"></a>
<span id="l1182">            ObjectIdentifier.newInternal(GIVENNAME_DATA);</span><a href="#l1182"></a>
<span id="l1183"></span><a href="#l1183"></a>
<span id="l1184">    // OID for the &quot;INITIALS=&quot; attribute, denoting a person's initials.</span><a href="#l1184"></a>
<span id="l1185">    public static final ObjectIdentifier INITIALS_OID =</span><a href="#l1185"></a>
<span id="l1186">            ObjectIdentifier.newInternal(INITIALS_DATA);</span><a href="#l1186"></a>
<span id="l1187"></span><a href="#l1187"></a>
<span id="l1188">    // OID for the &quot;GENERATION=&quot; attribute, denoting Jr., II, etc.</span><a href="#l1188"></a>
<span id="l1189">    public static final ObjectIdentifier GENERATIONQUALIFIER_OID =</span><a href="#l1189"></a>
<span id="l1190">            ObjectIdentifier.newInternal(GENERATIONQUALIFIER_DATA);</span><a href="#l1190"></a>
<span id="l1191"></span><a href="#l1191"></a>
<span id="l1192">    // OIDs from other sources which show up in X.500 names we</span><a href="#l1192"></a>
<span id="l1193">    // expect to deal with often.</span><a href="#l1193"></a>
<span id="l1194">    //</span><a href="#l1194"></a>
<span id="l1195">    // OID for &quot;IP=&quot; IP address attributes, used with SKIP.</span><a href="#l1195"></a>
<span id="l1196">    public static final ObjectIdentifier ipAddress_oid =</span><a href="#l1196"></a>
<span id="l1197">            ObjectIdentifier.newInternal(ipAddress_data);</span><a href="#l1197"></a>
<span id="l1198"></span><a href="#l1198"></a>
<span id="l1199">    // Domain component OID from RFC 1274, RFC 2247, RFC 5280.</span><a href="#l1199"></a>
<span id="l1200">    //</span><a href="#l1200"></a>
<span id="l1201">    // OID for &quot;DC=&quot; domain component attributes, used with DNSNames in DN</span><a href="#l1201"></a>
<span id="l1202">    // format.</span><a href="#l1202"></a>
<span id="l1203">    public static final ObjectIdentifier DOMAIN_COMPONENT_OID =</span><a href="#l1203"></a>
<span id="l1204">            ObjectIdentifier.newInternal(DOMAIN_COMPONENT_DATA);</span><a href="#l1204"></a>
<span id="l1205"></span><a href="#l1205"></a>
<span id="l1206">    // OID for &quot;UID=&quot; denoting a user id, defined in RFCs 1274 &amp; 2798.</span><a href="#l1206"></a>
<span id="l1207">    public static final ObjectIdentifier userid_oid =</span><a href="#l1207"></a>
<span id="l1208">            ObjectIdentifier.newInternal(userid_data);</span><a href="#l1208"></a>
<span id="l1209"></span><a href="#l1209"></a>
<span id="l1210">    /**</span><a href="#l1210"></a>
<span id="l1211">     * Return constraint type:&lt;ul&gt;</span><a href="#l1211"></a>
<span id="l1212">     *   &lt;li&gt;NAME_DIFF_TYPE = -1: input name is different type from this name</span><a href="#l1212"></a>
<span id="l1213">     *       (i.e. does not constrain)</span><a href="#l1213"></a>
<span id="l1214">     *   &lt;li&gt;NAME_MATCH = 0: input name matches this name</span><a href="#l1214"></a>
<span id="l1215">     *   &lt;li&gt;NAME_NARROWS = 1: input name narrows this name</span><a href="#l1215"></a>
<span id="l1216">     *   &lt;li&gt;NAME_WIDENS = 2: input name widens this name</span><a href="#l1216"></a>
<span id="l1217">     *   &lt;li&gt;NAME_SAME_TYPE = 3: input name does not match or narrow this name,</span><a href="#l1217"></a>
<span id="l1218">     &amp;       but is same type</span><a href="#l1218"></a>
<span id="l1219">     * &lt;/ul&gt;.  These results are used in checking NameConstraints during</span><a href="#l1219"></a>
<span id="l1220">     * certification path verification.</span><a href="#l1220"></a>
<span id="l1221">     *</span><a href="#l1221"></a>
<span id="l1222">     * @param inputName to be checked for being constrained</span><a href="#l1222"></a>
<span id="l1223">     * @returns constraint type above</span><a href="#l1223"></a>
<span id="l1224">     * @throws UnsupportedOperationException if name is not exact match, but</span><a href="#l1224"></a>
<span id="l1225">     *         narrowing and widening are not supported for this name type.</span><a href="#l1225"></a>
<span id="l1226">     */</span><a href="#l1226"></a>
<span id="l1227">    public int constrains(GeneralNameInterface inputName)</span><a href="#l1227"></a>
<span id="l1228">            throws UnsupportedOperationException {</span><a href="#l1228"></a>
<span id="l1229">        int constraintType;</span><a href="#l1229"></a>
<span id="l1230">        if (inputName == null) {</span><a href="#l1230"></a>
<span id="l1231">            constraintType = NAME_DIFF_TYPE;</span><a href="#l1231"></a>
<span id="l1232">        } else if (inputName.getType() != NAME_DIRECTORY) {</span><a href="#l1232"></a>
<span id="l1233">            constraintType = NAME_DIFF_TYPE;</span><a href="#l1233"></a>
<span id="l1234">        } else { // type == NAME_DIRECTORY</span><a href="#l1234"></a>
<span id="l1235">            X500Name inputX500 = (X500Name)inputName;</span><a href="#l1235"></a>
<span id="l1236">            if (inputX500.equals(this)) {</span><a href="#l1236"></a>
<span id="l1237">                constraintType = NAME_MATCH;</span><a href="#l1237"></a>
<span id="l1238">            } else if (inputX500.names.length == 0) {</span><a href="#l1238"></a>
<span id="l1239">                constraintType = NAME_WIDENS;</span><a href="#l1239"></a>
<span id="l1240">            } else if (this.names.length == 0) {</span><a href="#l1240"></a>
<span id="l1241">                constraintType = NAME_NARROWS;</span><a href="#l1241"></a>
<span id="l1242">            } else if (inputX500.isWithinSubtree(this)) {</span><a href="#l1242"></a>
<span id="l1243">                constraintType = NAME_NARROWS;</span><a href="#l1243"></a>
<span id="l1244">            } else if (isWithinSubtree(inputX500)) {</span><a href="#l1244"></a>
<span id="l1245">                constraintType = NAME_WIDENS;</span><a href="#l1245"></a>
<span id="l1246">            } else {</span><a href="#l1246"></a>
<span id="l1247">                constraintType = NAME_SAME_TYPE;</span><a href="#l1247"></a>
<span id="l1248">            }</span><a href="#l1248"></a>
<span id="l1249">        }</span><a href="#l1249"></a>
<span id="l1250">        return constraintType;</span><a href="#l1250"></a>
<span id="l1251">    }</span><a href="#l1251"></a>
<span id="l1252"></span><a href="#l1252"></a>
<span id="l1253">    /**</span><a href="#l1253"></a>
<span id="l1254">     * Compares this name with another and determines if</span><a href="#l1254"></a>
<span id="l1255">     * it is within the subtree of the other. Useful for</span><a href="#l1255"></a>
<span id="l1256">     * checking against the name constraints extension.</span><a href="#l1256"></a>
<span id="l1257">     *</span><a href="#l1257"></a>
<span id="l1258">     * @return true iff this name is within the subtree of other.</span><a href="#l1258"></a>
<span id="l1259">     */</span><a href="#l1259"></a>
<span id="l1260">    private boolean isWithinSubtree(X500Name other) {</span><a href="#l1260"></a>
<span id="l1261">        if (this == other) {</span><a href="#l1261"></a>
<span id="l1262">            return true;</span><a href="#l1262"></a>
<span id="l1263">        }</span><a href="#l1263"></a>
<span id="l1264">        if (other == null) {</span><a href="#l1264"></a>
<span id="l1265">            return false;</span><a href="#l1265"></a>
<span id="l1266">        }</span><a href="#l1266"></a>
<span id="l1267">        if (other.names.length == 0) {</span><a href="#l1267"></a>
<span id="l1268">            return true;</span><a href="#l1268"></a>
<span id="l1269">        }</span><a href="#l1269"></a>
<span id="l1270">        if (this.names.length == 0) {</span><a href="#l1270"></a>
<span id="l1271">            return false;</span><a href="#l1271"></a>
<span id="l1272">        }</span><a href="#l1272"></a>
<span id="l1273">        if (names.length &lt; other.names.length) {</span><a href="#l1273"></a>
<span id="l1274">            return false;</span><a href="#l1274"></a>
<span id="l1275">        }</span><a href="#l1275"></a>
<span id="l1276">        for (int i = 0; i &lt; other.names.length; i++) {</span><a href="#l1276"></a>
<span id="l1277">            if (!names[i].equals(other.names[i])) {</span><a href="#l1277"></a>
<span id="l1278">                return false;</span><a href="#l1278"></a>
<span id="l1279">            }</span><a href="#l1279"></a>
<span id="l1280">        }</span><a href="#l1280"></a>
<span id="l1281">        return true;</span><a href="#l1281"></a>
<span id="l1282">    }</span><a href="#l1282"></a>
<span id="l1283"></span><a href="#l1283"></a>
<span id="l1284">    /**</span><a href="#l1284"></a>
<span id="l1285">     * Return subtree depth of this name for purposes of determining</span><a href="#l1285"></a>
<span id="l1286">     * NameConstraints minimum and maximum bounds and for calculating</span><a href="#l1286"></a>
<span id="l1287">     * path lengths in name subtrees.</span><a href="#l1287"></a>
<span id="l1288">     *</span><a href="#l1288"></a>
<span id="l1289">     * @returns distance of name from root</span><a href="#l1289"></a>
<span id="l1290">     * @throws UnsupportedOperationException if not supported for this name type</span><a href="#l1290"></a>
<span id="l1291">     */</span><a href="#l1291"></a>
<span id="l1292">    public int subtreeDepth() throws UnsupportedOperationException {</span><a href="#l1292"></a>
<span id="l1293">        return names.length;</span><a href="#l1293"></a>
<span id="l1294">    }</span><a href="#l1294"></a>
<span id="l1295"></span><a href="#l1295"></a>
<span id="l1296">    /**</span><a href="#l1296"></a>
<span id="l1297">     * Return lowest common ancestor of this name and other name</span><a href="#l1297"></a>
<span id="l1298">     *</span><a href="#l1298"></a>
<span id="l1299">     * @param other another X500Name</span><a href="#l1299"></a>
<span id="l1300">     * @return X500Name of lowest common ancestor; null if none</span><a href="#l1300"></a>
<span id="l1301">     */</span><a href="#l1301"></a>
<span id="l1302">    public X500Name commonAncestor(X500Name other) {</span><a href="#l1302"></a>
<span id="l1303"></span><a href="#l1303"></a>
<span id="l1304">        if (other == null) {</span><a href="#l1304"></a>
<span id="l1305">            return null;</span><a href="#l1305"></a>
<span id="l1306">        }</span><a href="#l1306"></a>
<span id="l1307">        int otherLen = other.names.length;</span><a href="#l1307"></a>
<span id="l1308">        int thisLen = this.names.length;</span><a href="#l1308"></a>
<span id="l1309">        if (thisLen == 0 || otherLen == 0) {</span><a href="#l1309"></a>
<span id="l1310">            return null;</span><a href="#l1310"></a>
<span id="l1311">        }</span><a href="#l1311"></a>
<span id="l1312">        int minLen = (thisLen &lt; otherLen) ? thisLen: otherLen;</span><a href="#l1312"></a>
<span id="l1313"></span><a href="#l1313"></a>
<span id="l1314">        //Compare names from highest RDN down the naming tree</span><a href="#l1314"></a>
<span id="l1315">        //Note that these are stored in RDN[0]...</span><a href="#l1315"></a>
<span id="l1316">        int i=0;</span><a href="#l1316"></a>
<span id="l1317">        for (; i &lt; minLen; i++) {</span><a href="#l1317"></a>
<span id="l1318">            if (!names[i].equals(other.names[i])) {</span><a href="#l1318"></a>
<span id="l1319">                if (i == 0) {</span><a href="#l1319"></a>
<span id="l1320">                    return null;</span><a href="#l1320"></a>
<span id="l1321">                } else {</span><a href="#l1321"></a>
<span id="l1322">                    break;</span><a href="#l1322"></a>
<span id="l1323">                }</span><a href="#l1323"></a>
<span id="l1324">            }</span><a href="#l1324"></a>
<span id="l1325">        }</span><a href="#l1325"></a>
<span id="l1326"></span><a href="#l1326"></a>
<span id="l1327">        //Copy matching RDNs into new RDN array</span><a href="#l1327"></a>
<span id="l1328">        RDN[] ancestor = new RDN[i];</span><a href="#l1328"></a>
<span id="l1329">        for (int j=0; j &lt; i; j++) {</span><a href="#l1329"></a>
<span id="l1330">            ancestor[j] = names[j];</span><a href="#l1330"></a>
<span id="l1331">        }</span><a href="#l1331"></a>
<span id="l1332"></span><a href="#l1332"></a>
<span id="l1333">        X500Name commonAncestor = null;</span><a href="#l1333"></a>
<span id="l1334">        try {</span><a href="#l1334"></a>
<span id="l1335">            commonAncestor = new X500Name(ancestor);</span><a href="#l1335"></a>
<span id="l1336">        } catch (IOException ioe) {</span><a href="#l1336"></a>
<span id="l1337">            return null;</span><a href="#l1337"></a>
<span id="l1338">        }</span><a href="#l1338"></a>
<span id="l1339">        return commonAncestor;</span><a href="#l1339"></a>
<span id="l1340">    }</span><a href="#l1340"></a>
<span id="l1341"></span><a href="#l1341"></a>
<span id="l1342">    /**</span><a href="#l1342"></a>
<span id="l1343">     * Constructor object for use by asX500Principal().</span><a href="#l1343"></a>
<span id="l1344">     */</span><a href="#l1344"></a>
<span id="l1345">    private static final Constructor&lt;X500Principal&gt; principalConstructor;</span><a href="#l1345"></a>
<span id="l1346"></span><a href="#l1346"></a>
<span id="l1347">    /**</span><a href="#l1347"></a>
<span id="l1348">     * Field object for use by asX500Name().</span><a href="#l1348"></a>
<span id="l1349">     */</span><a href="#l1349"></a>
<span id="l1350">    private static final Field principalField;</span><a href="#l1350"></a>
<span id="l1351"></span><a href="#l1351"></a>
<span id="l1352">    /**</span><a href="#l1352"></a>
<span id="l1353">     * Retrieve the Constructor and Field we need for reflective access</span><a href="#l1353"></a>
<span id="l1354">     * and make them accessible.</span><a href="#l1354"></a>
<span id="l1355">     */</span><a href="#l1355"></a>
<span id="l1356">    static {</span><a href="#l1356"></a>
<span id="l1357">        PrivilegedExceptionAction&lt;Object[]&gt; pa =</span><a href="#l1357"></a>
<span id="l1358">                new PrivilegedExceptionAction&lt;Object[]&gt;() {</span><a href="#l1358"></a>
<span id="l1359">            public Object[] run() throws Exception {</span><a href="#l1359"></a>
<span id="l1360">                Class&lt;X500Principal&gt; pClass = X500Principal.class;</span><a href="#l1360"></a>
<span id="l1361">                Class&lt;?&gt;[] args = new Class&lt;?&gt;[] { X500Name.class };</span><a href="#l1361"></a>
<span id="l1362">                Constructor&lt;X500Principal&gt; cons = pClass.getDeclaredConstructor(args);</span><a href="#l1362"></a>
<span id="l1363">                cons.setAccessible(true);</span><a href="#l1363"></a>
<span id="l1364">                Field field = pClass.getDeclaredField(&quot;thisX500Name&quot;);</span><a href="#l1364"></a>
<span id="l1365">                field.setAccessible(true);</span><a href="#l1365"></a>
<span id="l1366">                return new Object[] {cons, field};</span><a href="#l1366"></a>
<span id="l1367">            }</span><a href="#l1367"></a>
<span id="l1368">        };</span><a href="#l1368"></a>
<span id="l1369">        try {</span><a href="#l1369"></a>
<span id="l1370">            Object[] result = AccessController.doPrivileged(pa);</span><a href="#l1370"></a>
<span id="l1371">            @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l1371"></a>
<span id="l1372">            Constructor&lt;X500Principal&gt; constr =</span><a href="#l1372"></a>
<span id="l1373">                    (Constructor&lt;X500Principal&gt;)result[0];</span><a href="#l1373"></a>
<span id="l1374">            principalConstructor = constr;</span><a href="#l1374"></a>
<span id="l1375">            principalField = (Field)result[1];</span><a href="#l1375"></a>
<span id="l1376">        } catch (Exception e) {</span><a href="#l1376"></a>
<span id="l1377">            throw new InternalError(&quot;Could not obtain X500Principal access&quot;, e);</span><a href="#l1377"></a>
<span id="l1378">        }</span><a href="#l1378"></a>
<span id="l1379">    }</span><a href="#l1379"></a>
<span id="l1380"></span><a href="#l1380"></a>
<span id="l1381">    /**</span><a href="#l1381"></a>
<span id="l1382">     * Get an X500Principal backed by this X500Name.</span><a href="#l1382"></a>
<span id="l1383">     *</span><a href="#l1383"></a>
<span id="l1384">     * Note that we are using privileged reflection to access the hidden</span><a href="#l1384"></a>
<span id="l1385">     * package private constructor in X500Principal.</span><a href="#l1385"></a>
<span id="l1386">     */</span><a href="#l1386"></a>
<span id="l1387">    public X500Principal asX500Principal() {</span><a href="#l1387"></a>
<span id="l1388">        if (x500Principal == null) {</span><a href="#l1388"></a>
<span id="l1389">            try {</span><a href="#l1389"></a>
<span id="l1390">                Object[] args = new Object[] {this};</span><a href="#l1390"></a>
<span id="l1391">                x500Principal = principalConstructor.newInstance(args);</span><a href="#l1391"></a>
<span id="l1392">            } catch (Exception e) {</span><a href="#l1392"></a>
<span id="l1393">                throw new RuntimeException(&quot;Unexpected exception&quot;, e);</span><a href="#l1393"></a>
<span id="l1394">            }</span><a href="#l1394"></a>
<span id="l1395">        }</span><a href="#l1395"></a>
<span id="l1396">        return x500Principal;</span><a href="#l1396"></a>
<span id="l1397">    }</span><a href="#l1397"></a>
<span id="l1398"></span><a href="#l1398"></a>
<span id="l1399">    /**</span><a href="#l1399"></a>
<span id="l1400">     * Get the X500Name contained in the given X500Principal.</span><a href="#l1400"></a>
<span id="l1401">     *</span><a href="#l1401"></a>
<span id="l1402">     * Note that the X500Name is retrieved using reflection.</span><a href="#l1402"></a>
<span id="l1403">     */</span><a href="#l1403"></a>
<span id="l1404">    public static X500Name asX500Name(X500Principal p) {</span><a href="#l1404"></a>
<span id="l1405">        try {</span><a href="#l1405"></a>
<span id="l1406">            X500Name name = (X500Name)principalField.get(p);</span><a href="#l1406"></a>
<span id="l1407">            name.x500Principal = p;</span><a href="#l1407"></a>
<span id="l1408">            return name;</span><a href="#l1408"></a>
<span id="l1409">        } catch (Exception e) {</span><a href="#l1409"></a>
<span id="l1410">            throw new RuntimeException(&quot;Unexpected exception&quot;, e);</span><a href="#l1410"></a>
<span id="l1411">        }</span><a href="#l1411"></a>
<span id="l1412">    }</span><a href="#l1412"></a>
<span id="l1413"></span><a href="#l1413"></a>
<span id="l1414">}</span><a href="#l1414"></a></pre>
<div class="sourcelast"></div>
</div>
</div>
</div>



<div class="container"><div class="main footer">
&copy 2007, <script>document.write(new Date().getFullYear())</script> Oracle and/or its affiliates<br/>
<a href="http://openjdk.java.net/legal/tou/">Terms of Use</a>
&#183; <a href="http://www.oracle.com/us/legal/privacy/">Privacy</a>
&#183; <a href="https://openjdk.java.net/legal/openjdk-trademark-notice.html">Trademarks</a>
</div></div>

</body>
</html>

