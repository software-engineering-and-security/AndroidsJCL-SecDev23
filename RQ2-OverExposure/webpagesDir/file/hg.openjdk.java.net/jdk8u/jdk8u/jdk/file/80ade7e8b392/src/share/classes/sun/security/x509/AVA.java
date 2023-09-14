<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 80ade7e8b392 src/share/classes/sun/security/x509/AVA.java</title>
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
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/x509/AVA.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/80ade7e8b392/src/share/classes/sun/security/x509/AVA.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/80ade7e8b392/src/share/classes/sun/security/x509/AVA.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/80ade7e8b392/src/share/classes/sun/security/x509/AVA.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/80ade7e8b392/src/share/classes/sun/security/x509/AVA.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/80ade7e8b392/src/share/classes/sun/security/x509/AVA.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/x509/AVA.java @ 13797:80ade7e8b392</h3>

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
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/52bc200b14e5/src/share/classes/sun/security/x509/AVA.java">52bc200b14e5</a> </td>
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
<span id="l2"> * Copyright (c) 1996, 2011, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.ByteArrayOutputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.IOException;</span><a href="#l29"></a>
<span id="l30">import java.io.OutputStream;</span><a href="#l30"></a>
<span id="l31">import java.io.Reader;</span><a href="#l31"></a>
<span id="l32">import java.security.AccessController;</span><a href="#l32"></a>
<span id="l33">import java.text.Normalizer;</span><a href="#l33"></a>
<span id="l34">import java.util.*;</span><a href="#l34"></a>
<span id="l35"></span><a href="#l35"></a>
<span id="l36">import sun.security.action.GetBooleanAction;</span><a href="#l36"></a>
<span id="l37">import sun.security.util.*;</span><a href="#l37"></a>
<span id="l38">import sun.security.pkcs.PKCS9Attribute;</span><a href="#l38"></a>
<span id="l39"></span><a href="#l39"></a>
<span id="l40"></span><a href="#l40"></a>
<span id="l41">/**</span><a href="#l41"></a>
<span id="l42"> * X.500 Attribute-Value-Assertion (AVA):  an attribute, as identified by</span><a href="#l42"></a>
<span id="l43"> * some attribute ID, has some particular value.  Values are as a rule ASN.1</span><a href="#l43"></a>
<span id="l44"> * printable strings.  A conventional set of type IDs is recognized when</span><a href="#l44"></a>
<span id="l45"> * parsing (and generating) RFC 1779, 2253 or 4514 syntax strings.</span><a href="#l45"></a>
<span id="l46"> *</span><a href="#l46"></a>
<span id="l47"> * &lt;P&gt;AVAs are components of X.500 relative names.  Think of them as being</span><a href="#l47"></a>
<span id="l48"> * individual fields of a database record.  The attribute ID is how you</span><a href="#l48"></a>
<span id="l49"> * identify the field, and the value is part of a particular record.</span><a href="#l49"></a>
<span id="l50"> * &lt;p&gt;</span><a href="#l50"></a>
<span id="l51"> * Note that instances of this class are immutable.</span><a href="#l51"></a>
<span id="l52"> *</span><a href="#l52"></a>
<span id="l53"> * @see X500Name</span><a href="#l53"></a>
<span id="l54"> * @see RDN</span><a href="#l54"></a>
<span id="l55"> *</span><a href="#l55"></a>
<span id="l56"> *</span><a href="#l56"></a>
<span id="l57"> * @author David Brownell</span><a href="#l57"></a>
<span id="l58"> * @author Amit Kapoor</span><a href="#l58"></a>
<span id="l59"> * @author Hemma Prafullchandra</span><a href="#l59"></a>
<span id="l60"> */</span><a href="#l60"></a>
<span id="l61">public class AVA implements DerEncoder {</span><a href="#l61"></a>
<span id="l62"></span><a href="#l62"></a>
<span id="l63">    private static final Debug debug = Debug.getInstance(&quot;x509&quot;, &quot;\t[AVA]&quot;);</span><a href="#l63"></a>
<span id="l64">    // See CR 6391482: if enabled this flag preserves the old but incorrect</span><a href="#l64"></a>
<span id="l65">    // PrintableString encoding for DomainComponent. It may need to be set to</span><a href="#l65"></a>
<span id="l66">    // avoid breaking preexisting certificates generated with sun.security APIs.</span><a href="#l66"></a>
<span id="l67">    private static final boolean PRESERVE_OLD_DC_ENCODING =</span><a href="#l67"></a>
<span id="l68">        AccessController.doPrivileged(new GetBooleanAction</span><a href="#l68"></a>
<span id="l69">            (&quot;com.sun.security.preserveOldDCEncoding&quot;));</span><a href="#l69"></a>
<span id="l70"></span><a href="#l70"></a>
<span id="l71">    /**</span><a href="#l71"></a>
<span id="l72">     * DEFAULT format allows both RFC1779 and RFC2253 syntax and</span><a href="#l72"></a>
<span id="l73">     * additional keywords.</span><a href="#l73"></a>
<span id="l74">     */</span><a href="#l74"></a>
<span id="l75">    final static int DEFAULT = 1;</span><a href="#l75"></a>
<span id="l76">    /**</span><a href="#l76"></a>
<span id="l77">     * RFC1779 specifies format according to RFC1779.</span><a href="#l77"></a>
<span id="l78">     */</span><a href="#l78"></a>
<span id="l79">    final static int RFC1779 = 2;</span><a href="#l79"></a>
<span id="l80">    /**</span><a href="#l80"></a>
<span id="l81">     * RFC2253 specifies format according to RFC2253.</span><a href="#l81"></a>
<span id="l82">     */</span><a href="#l82"></a>
<span id="l83">    final static int RFC2253 = 3;</span><a href="#l83"></a>
<span id="l84"></span><a href="#l84"></a>
<span id="l85">    // currently not private, accessed directly from RDN</span><a href="#l85"></a>
<span id="l86">    final ObjectIdentifier oid;</span><a href="#l86"></a>
<span id="l87">    final DerValue value;</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    /*</span><a href="#l89"></a>
<span id="l90">     * If the value has any of these characters in it, it must be quoted.</span><a href="#l90"></a>
<span id="l91">     * Backslash and quote characters must also be individually escaped.</span><a href="#l91"></a>
<span id="l92">     * Leading and trailing spaces, also multiple internal spaces, also</span><a href="#l92"></a>
<span id="l93">     * call for quoting the whole string.</span><a href="#l93"></a>
<span id="l94">     */</span><a href="#l94"></a>
<span id="l95">    private static final String specialChars1779 = &quot;,=\n+&lt;&gt;#;\\\&quot;&quot;;</span><a href="#l95"></a>
<span id="l96"></span><a href="#l96"></a>
<span id="l97">    /*</span><a href="#l97"></a>
<span id="l98">     * In RFC2253, if the value has any of these characters in it, it</span><a href="#l98"></a>
<span id="l99">     * must be quoted by a preceding \.</span><a href="#l99"></a>
<span id="l100">     */</span><a href="#l100"></a>
<span id="l101">    private static final String specialChars2253 = &quot;,=+&lt;&gt;#;\\\&quot;&quot;;</span><a href="#l101"></a>
<span id="l102"></span><a href="#l102"></a>
<span id="l103">    /*</span><a href="#l103"></a>
<span id="l104">     * includes special chars from RFC1779 and RFC2253, as well as ' ' from</span><a href="#l104"></a>
<span id="l105">     * RFC 4514.</span><a href="#l105"></a>
<span id="l106">     */</span><a href="#l106"></a>
<span id="l107">    private static final String specialCharsDefault = &quot;,=\n+&lt;&gt;#;\\\&quot; &quot;;</span><a href="#l107"></a>
<span id="l108">    private static final String escapedDefault = &quot;,+&lt;&gt;;\&quot;&quot;;</span><a href="#l108"></a>
<span id="l109"></span><a href="#l109"></a>
<span id="l110">    /*</span><a href="#l110"></a>
<span id="l111">     * Values that aren't printable strings are emitted as BER-encoded</span><a href="#l111"></a>
<span id="l112">     * hex data.</span><a href="#l112"></a>
<span id="l113">     */</span><a href="#l113"></a>
<span id="l114">    private static final String hexDigits = &quot;0123456789ABCDEF&quot;;</span><a href="#l114"></a>
<span id="l115"></span><a href="#l115"></a>
<span id="l116">    public AVA(ObjectIdentifier type, DerValue val) {</span><a href="#l116"></a>
<span id="l117">        if ((type == null) || (val == null)) {</span><a href="#l117"></a>
<span id="l118">            throw new NullPointerException();</span><a href="#l118"></a>
<span id="l119">        }</span><a href="#l119"></a>
<span id="l120">        oid = type;</span><a href="#l120"></a>
<span id="l121">        value = val;</span><a href="#l121"></a>
<span id="l122">    }</span><a href="#l122"></a>
<span id="l123"></span><a href="#l123"></a>
<span id="l124">    /**</span><a href="#l124"></a>
<span id="l125">     * Parse an RFC 1779, 2253 or 4514 style AVA string:  CN=fee fie foe fum</span><a href="#l125"></a>
<span id="l126">     * or perhaps with quotes.  Not all defined AVA tags are supported;</span><a href="#l126"></a>
<span id="l127">     * of current note are X.400 related ones (PRMD, ADMD, etc).</span><a href="#l127"></a>
<span id="l128">     *</span><a href="#l128"></a>
<span id="l129">     * This terminates at unescaped AVA separators (&quot;+&quot;) or RDN</span><a href="#l129"></a>
<span id="l130">     * separators (&quot;,&quot;, &quot;;&quot;), and removes cosmetic whitespace at the end of</span><a href="#l130"></a>
<span id="l131">     * values.</span><a href="#l131"></a>
<span id="l132">     */</span><a href="#l132"></a>
<span id="l133">    AVA(Reader in) throws IOException {</span><a href="#l133"></a>
<span id="l134">        this(in, DEFAULT);</span><a href="#l134"></a>
<span id="l135">    }</span><a href="#l135"></a>
<span id="l136"></span><a href="#l136"></a>
<span id="l137">    /**</span><a href="#l137"></a>
<span id="l138">     * Parse an RFC 1779, 2253 or 4514 style AVA string:  CN=fee fie foe fum</span><a href="#l138"></a>
<span id="l139">     * or perhaps with quotes. Additional keywords can be specified in the</span><a href="#l139"></a>
<span id="l140">     * keyword/OID map.</span><a href="#l140"></a>
<span id="l141">     *</span><a href="#l141"></a>
<span id="l142">     * This terminates at unescaped AVA separators (&quot;+&quot;) or RDN</span><a href="#l142"></a>
<span id="l143">     * separators (&quot;,&quot;, &quot;;&quot;), and removes cosmetic whitespace at the end of</span><a href="#l143"></a>
<span id="l144">     * values.</span><a href="#l144"></a>
<span id="l145">     */</span><a href="#l145"></a>
<span id="l146">    AVA(Reader in, Map&lt;String, String&gt; keywordMap) throws IOException {</span><a href="#l146"></a>
<span id="l147">        this(in, DEFAULT, keywordMap);</span><a href="#l147"></a>
<span id="l148">    }</span><a href="#l148"></a>
<span id="l149"></span><a href="#l149"></a>
<span id="l150">    /**</span><a href="#l150"></a>
<span id="l151">     * Parse an AVA string formatted according to format.</span><a href="#l151"></a>
<span id="l152">     */</span><a href="#l152"></a>
<span id="l153">    AVA(Reader in, int format) throws IOException {</span><a href="#l153"></a>
<span id="l154">        this(in, format, Collections.&lt;String, String&gt;emptyMap());</span><a href="#l154"></a>
<span id="l155">    }</span><a href="#l155"></a>
<span id="l156"></span><a href="#l156"></a>
<span id="l157">    /**</span><a href="#l157"></a>
<span id="l158">     * Parse an AVA string formatted according to format.</span><a href="#l158"></a>
<span id="l159">     *</span><a href="#l159"></a>
<span id="l160">     * @param in Reader containing AVA String</span><a href="#l160"></a>
<span id="l161">     * @param format parsing format</span><a href="#l161"></a>
<span id="l162">     * @param keywordMap a Map where a keyword String maps to a corresponding</span><a href="#l162"></a>
<span id="l163">     *   OID String. Each AVA keyword will be mapped to the corresponding OID.</span><a href="#l163"></a>
<span id="l164">     *   If an entry does not exist, it will fallback to the builtin</span><a href="#l164"></a>
<span id="l165">     *   keyword/OID mapping.</span><a href="#l165"></a>
<span id="l166">     * @throws IOException if the AVA String is not valid in the specified</span><a href="#l166"></a>
<span id="l167">     *   format or an OID String from the keywordMap is improperly formatted</span><a href="#l167"></a>
<span id="l168">     */</span><a href="#l168"></a>
<span id="l169">    AVA(Reader in, int format, Map&lt;String, String&gt; keywordMap)</span><a href="#l169"></a>
<span id="l170">        throws IOException {</span><a href="#l170"></a>
<span id="l171">        // assume format is one of DEFAULT or RFC2253</span><a href="#l171"></a>
<span id="l172"></span><a href="#l172"></a>
<span id="l173">        StringBuilder   temp = new StringBuilder();</span><a href="#l173"></a>
<span id="l174">        int             c;</span><a href="#l174"></a>
<span id="l175"></span><a href="#l175"></a>
<span id="l176">        /*</span><a href="#l176"></a>
<span id="l177">         * First get the keyword indicating the attribute's type,</span><a href="#l177"></a>
<span id="l178">         * and map it to the appropriate OID.</span><a href="#l178"></a>
<span id="l179">         */</span><a href="#l179"></a>
<span id="l180">        while (true) {</span><a href="#l180"></a>
<span id="l181">            c = readChar(in, &quot;Incorrect AVA format&quot;);</span><a href="#l181"></a>
<span id="l182">            if (c == '=') {</span><a href="#l182"></a>
<span id="l183">                break;</span><a href="#l183"></a>
<span id="l184">            }</span><a href="#l184"></a>
<span id="l185">            temp.append((char)c);</span><a href="#l185"></a>
<span id="l186">        }</span><a href="#l186"></a>
<span id="l187"></span><a href="#l187"></a>
<span id="l188">        oid = AVAKeyword.getOID(temp.toString(), format, keywordMap);</span><a href="#l188"></a>
<span id="l189"></span><a href="#l189"></a>
<span id="l190">        /*</span><a href="#l190"></a>
<span id="l191">         * Now parse the value.  &quot;#hex&quot;, a quoted string, or a string</span><a href="#l191"></a>
<span id="l192">         * terminated by &quot;+&quot;, &quot;,&quot;, &quot;;&quot;.  Whitespace before or after</span><a href="#l192"></a>
<span id="l193">         * the value is stripped away unless format is RFC2253.</span><a href="#l193"></a>
<span id="l194">         */</span><a href="#l194"></a>
<span id="l195">        temp.setLength(0);</span><a href="#l195"></a>
<span id="l196">        if (format == RFC2253) {</span><a href="#l196"></a>
<span id="l197">            // read next character</span><a href="#l197"></a>
<span id="l198">            c = in.read();</span><a href="#l198"></a>
<span id="l199">            if (c == ' ') {</span><a href="#l199"></a>
<span id="l200">                throw new IOException(&quot;Incorrect AVA RFC2253 format - &quot; +</span><a href="#l200"></a>
<span id="l201">                                      &quot;leading space must be escaped&quot;);</span><a href="#l201"></a>
<span id="l202">            }</span><a href="#l202"></a>
<span id="l203">        } else {</span><a href="#l203"></a>
<span id="l204">            // read next character skipping whitespace</span><a href="#l204"></a>
<span id="l205">            do {</span><a href="#l205"></a>
<span id="l206">                c = in.read();</span><a href="#l206"></a>
<span id="l207">            } while ((c == ' ') || (c == '\n'));</span><a href="#l207"></a>
<span id="l208">        }</span><a href="#l208"></a>
<span id="l209">        if (c == -1) {</span><a href="#l209"></a>
<span id="l210">            // empty value</span><a href="#l210"></a>
<span id="l211">            value = new DerValue(&quot;&quot;);</span><a href="#l211"></a>
<span id="l212">            return;</span><a href="#l212"></a>
<span id="l213">        }</span><a href="#l213"></a>
<span id="l214"></span><a href="#l214"></a>
<span id="l215">        if (c == '#') {</span><a href="#l215"></a>
<span id="l216">            value = parseHexString(in, format);</span><a href="#l216"></a>
<span id="l217">        } else if ((c == '&quot;') &amp;&amp; (format != RFC2253)) {</span><a href="#l217"></a>
<span id="l218">            value = parseQuotedString(in, temp);</span><a href="#l218"></a>
<span id="l219">        } else {</span><a href="#l219"></a>
<span id="l220">            value = parseString(in, c, format, temp);</span><a href="#l220"></a>
<span id="l221">        }</span><a href="#l221"></a>
<span id="l222">    }</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">    /**</span><a href="#l224"></a>
<span id="l225">     * Get the ObjectIdentifier of this AVA.</span><a href="#l225"></a>
<span id="l226">     */</span><a href="#l226"></a>
<span id="l227">    public ObjectIdentifier getObjectIdentifier() {</span><a href="#l227"></a>
<span id="l228">        return oid;</span><a href="#l228"></a>
<span id="l229">    }</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">    /**</span><a href="#l231"></a>
<span id="l232">     * Get the value of this AVA as a DerValue.</span><a href="#l232"></a>
<span id="l233">     */</span><a href="#l233"></a>
<span id="l234">    public DerValue getDerValue() {</span><a href="#l234"></a>
<span id="l235">        return value;</span><a href="#l235"></a>
<span id="l236">    }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">    /**</span><a href="#l238"></a>
<span id="l239">     * Get the value of this AVA as a String.</span><a href="#l239"></a>
<span id="l240">     *</span><a href="#l240"></a>
<span id="l241">     * @exception RuntimeException if we could not obtain the string form</span><a href="#l241"></a>
<span id="l242">     *    (should not occur)</span><a href="#l242"></a>
<span id="l243">     */</span><a href="#l243"></a>
<span id="l244">    public String getValueString() {</span><a href="#l244"></a>
<span id="l245">        try {</span><a href="#l245"></a>
<span id="l246">            String s = value.getAsString();</span><a href="#l246"></a>
<span id="l247">            if (s == null) {</span><a href="#l247"></a>
<span id="l248">                throw new RuntimeException(&quot;AVA string is null&quot;);</span><a href="#l248"></a>
<span id="l249">            }</span><a href="#l249"></a>
<span id="l250">            return s;</span><a href="#l250"></a>
<span id="l251">        } catch (IOException e) {</span><a href="#l251"></a>
<span id="l252">            // should not occur</span><a href="#l252"></a>
<span id="l253">            throw new RuntimeException(&quot;AVA error: &quot; + e, e);</span><a href="#l253"></a>
<span id="l254">        }</span><a href="#l254"></a>
<span id="l255">    }</span><a href="#l255"></a>
<span id="l256"></span><a href="#l256"></a>
<span id="l257">    private static DerValue parseHexString</span><a href="#l257"></a>
<span id="l258">        (Reader in, int format) throws IOException {</span><a href="#l258"></a>
<span id="l259"></span><a href="#l259"></a>
<span id="l260">        int c;</span><a href="#l260"></a>
<span id="l261">        ByteArrayOutputStream baos = new ByteArrayOutputStream();</span><a href="#l261"></a>
<span id="l262">        byte b = 0;</span><a href="#l262"></a>
<span id="l263">        int cNdx = 0;</span><a href="#l263"></a>
<span id="l264">        while (true) {</span><a href="#l264"></a>
<span id="l265">            c = in.read();</span><a href="#l265"></a>
<span id="l266"></span><a href="#l266"></a>
<span id="l267">            if (isTerminator(c, format)) {</span><a href="#l267"></a>
<span id="l268">                break;</span><a href="#l268"></a>
<span id="l269">            }</span><a href="#l269"></a>
<span id="l270"></span><a href="#l270"></a>
<span id="l271">            int cVal = hexDigits.indexOf(Character.toUpperCase((char)c));</span><a href="#l271"></a>
<span id="l272"></span><a href="#l272"></a>
<span id="l273">            if (cVal == -1) {</span><a href="#l273"></a>
<span id="l274">                throw new IOException(&quot;AVA parse, invalid hex &quot; +</span><a href="#l274"></a>
<span id="l275">                                              &quot;digit: &quot;+ (char)c);</span><a href="#l275"></a>
<span id="l276">            }</span><a href="#l276"></a>
<span id="l277"></span><a href="#l277"></a>
<span id="l278">            if ((cNdx % 2) == 1) {</span><a href="#l278"></a>
<span id="l279">                b = (byte)((b * 16) + (byte)(cVal));</span><a href="#l279"></a>
<span id="l280">                baos.write(b);</span><a href="#l280"></a>
<span id="l281">            } else {</span><a href="#l281"></a>
<span id="l282">                b = (byte)(cVal);</span><a href="#l282"></a>
<span id="l283">            }</span><a href="#l283"></a>
<span id="l284">            cNdx++;</span><a href="#l284"></a>
<span id="l285">        }</span><a href="#l285"></a>
<span id="l286"></span><a href="#l286"></a>
<span id="l287">        // throw exception if no hex digits</span><a href="#l287"></a>
<span id="l288">        if (cNdx == 0) {</span><a href="#l288"></a>
<span id="l289">            throw new IOException(&quot;AVA parse, zero hex digits&quot;);</span><a href="#l289"></a>
<span id="l290">        }</span><a href="#l290"></a>
<span id="l291"></span><a href="#l291"></a>
<span id="l292">        // throw exception if odd number of hex digits</span><a href="#l292"></a>
<span id="l293">        if (cNdx % 2 == 1) {</span><a href="#l293"></a>
<span id="l294">            throw new IOException(&quot;AVA parse, odd number of hex digits&quot;);</span><a href="#l294"></a>
<span id="l295">        }</span><a href="#l295"></a>
<span id="l296"></span><a href="#l296"></a>
<span id="l297">        return new DerValue(baos.toByteArray());</span><a href="#l297"></a>
<span id="l298">    }</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">    private DerValue parseQuotedString</span><a href="#l300"></a>
<span id="l301">        (Reader in, StringBuilder temp) throws IOException {</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">        // RFC1779 specifies that an entire RDN may be enclosed in double</span><a href="#l303"></a>
<span id="l304">        // quotes. In this case the syntax is any sequence of</span><a href="#l304"></a>
<span id="l305">        // backslash-specialChar, backslash-backslash,</span><a href="#l305"></a>
<span id="l306">        // backslash-doublequote, or character other than backslash or</span><a href="#l306"></a>
<span id="l307">        // doublequote.</span><a href="#l307"></a>
<span id="l308">        int c = readChar(in, &quot;Quoted string did not end in quote&quot;);</span><a href="#l308"></a>
<span id="l309"></span><a href="#l309"></a>
<span id="l310">        List&lt;Byte&gt; embeddedHex = new ArrayList&lt;Byte&gt;();</span><a href="#l310"></a>
<span id="l311">        boolean isPrintableString = true;</span><a href="#l311"></a>
<span id="l312">        while (c != '&quot;') {</span><a href="#l312"></a>
<span id="l313">            if (c == '\\') {</span><a href="#l313"></a>
<span id="l314">                c = readChar(in, &quot;Quoted string did not end in quote&quot;);</span><a href="#l314"></a>
<span id="l315"></span><a href="#l315"></a>
<span id="l316">                // check for embedded hex pairs</span><a href="#l316"></a>
<span id="l317">                Byte hexByte = null;</span><a href="#l317"></a>
<span id="l318">                if ((hexByte = getEmbeddedHexPair(c, in)) != null) {</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">                    // always encode AVAs with embedded hex as UTF8</span><a href="#l320"></a>
<span id="l321">                    isPrintableString = false;</span><a href="#l321"></a>
<span id="l322"></span><a href="#l322"></a>
<span id="l323">                    // append consecutive embedded hex</span><a href="#l323"></a>
<span id="l324">                    // as single string later</span><a href="#l324"></a>
<span id="l325">                    embeddedHex.add(hexByte);</span><a href="#l325"></a>
<span id="l326">                    c = in.read();</span><a href="#l326"></a>
<span id="l327">                    continue;</span><a href="#l327"></a>
<span id="l328">                }</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">                if (specialChars1779.indexOf((char)c) &lt; 0) {</span><a href="#l330"></a>
<span id="l331">                    throw new IOException</span><a href="#l331"></a>
<span id="l332">                        (&quot;Invalid escaped character in AVA: &quot; +</span><a href="#l332"></a>
<span id="l333">                        (char)c);</span><a href="#l333"></a>
<span id="l334">                }</span><a href="#l334"></a>
<span id="l335">            }</span><a href="#l335"></a>
<span id="l336"></span><a href="#l336"></a>
<span id="l337">            // add embedded hex bytes before next char</span><a href="#l337"></a>
<span id="l338">            if (embeddedHex.size() &gt; 0) {</span><a href="#l338"></a>
<span id="l339">                String hexString = getEmbeddedHexString(embeddedHex);</span><a href="#l339"></a>
<span id="l340">                temp.append(hexString);</span><a href="#l340"></a>
<span id="l341">                embeddedHex.clear();</span><a href="#l341"></a>
<span id="l342">            }</span><a href="#l342"></a>
<span id="l343"></span><a href="#l343"></a>
<span id="l344">            // check for non-PrintableString chars</span><a href="#l344"></a>
<span id="l345">            isPrintableString &amp;= DerValue.isPrintableStringChar((char)c);</span><a href="#l345"></a>
<span id="l346">            temp.append((char)c);</span><a href="#l346"></a>
<span id="l347">            c = readChar(in, &quot;Quoted string did not end in quote&quot;);</span><a href="#l347"></a>
<span id="l348">        }</span><a href="#l348"></a>
<span id="l349"></span><a href="#l349"></a>
<span id="l350">        // add trailing embedded hex bytes</span><a href="#l350"></a>
<span id="l351">        if (embeddedHex.size() &gt; 0) {</span><a href="#l351"></a>
<span id="l352">            String hexString = getEmbeddedHexString(embeddedHex);</span><a href="#l352"></a>
<span id="l353">            temp.append(hexString);</span><a href="#l353"></a>
<span id="l354">            embeddedHex.clear();</span><a href="#l354"></a>
<span id="l355">        }</span><a href="#l355"></a>
<span id="l356"></span><a href="#l356"></a>
<span id="l357">        do {</span><a href="#l357"></a>
<span id="l358">            c = in.read();</span><a href="#l358"></a>
<span id="l359">        } while ((c == '\n') || (c == ' '));</span><a href="#l359"></a>
<span id="l360">        if (c != -1) {</span><a href="#l360"></a>
<span id="l361">            throw new IOException(&quot;AVA had characters other than &quot;</span><a href="#l361"></a>
<span id="l362">                    + &quot;whitespace after terminating quote&quot;);</span><a href="#l362"></a>
<span id="l363">        }</span><a href="#l363"></a>
<span id="l364"></span><a href="#l364"></a>
<span id="l365">        // encode as PrintableString unless value contains</span><a href="#l365"></a>
<span id="l366">        // non-PrintableString chars</span><a href="#l366"></a>
<span id="l367">        if (this.oid.equals((Object)PKCS9Attribute.EMAIL_ADDRESS_OID) ||</span><a href="#l367"></a>
<span id="l368">            (this.oid.equals((Object)X500Name.DOMAIN_COMPONENT_OID) &amp;&amp;</span><a href="#l368"></a>
<span id="l369">                PRESERVE_OLD_DC_ENCODING == false)) {</span><a href="#l369"></a>
<span id="l370">            // EmailAddress and DomainComponent must be IA5String</span><a href="#l370"></a>
<span id="l371">            return new DerValue(DerValue.tag_IA5String,</span><a href="#l371"></a>
<span id="l372">                                        temp.toString().trim());</span><a href="#l372"></a>
<span id="l373">        } else if (isPrintableString) {</span><a href="#l373"></a>
<span id="l374">            return new DerValue(temp.toString().trim());</span><a href="#l374"></a>
<span id="l375">        } else {</span><a href="#l375"></a>
<span id="l376">            return new DerValue(DerValue.tag_UTF8String,</span><a href="#l376"></a>
<span id="l377">                                        temp.toString().trim());</span><a href="#l377"></a>
<span id="l378">        }</span><a href="#l378"></a>
<span id="l379">    }</span><a href="#l379"></a>
<span id="l380"></span><a href="#l380"></a>
<span id="l381">    private DerValue parseString</span><a href="#l381"></a>
<span id="l382">        (Reader in, int c, int format, StringBuilder temp) throws IOException {</span><a href="#l382"></a>
<span id="l383"></span><a href="#l383"></a>
<span id="l384">        List&lt;Byte&gt; embeddedHex = new ArrayList&lt;&gt;();</span><a href="#l384"></a>
<span id="l385">        boolean isPrintableString = true;</span><a href="#l385"></a>
<span id="l386">        boolean escape = false;</span><a href="#l386"></a>
<span id="l387">        boolean leadingChar = true;</span><a href="#l387"></a>
<span id="l388">        int spaceCount = 0;</span><a href="#l388"></a>
<span id="l389">        do {</span><a href="#l389"></a>
<span id="l390">            escape = false;</span><a href="#l390"></a>
<span id="l391">            if (c == '\\') {</span><a href="#l391"></a>
<span id="l392">                escape = true;</span><a href="#l392"></a>
<span id="l393">                c = readChar(in, &quot;Invalid trailing backslash&quot;);</span><a href="#l393"></a>
<span id="l394"></span><a href="#l394"></a>
<span id="l395">                // check for embedded hex pairs</span><a href="#l395"></a>
<span id="l396">                Byte hexByte = null;</span><a href="#l396"></a>
<span id="l397">                if ((hexByte = getEmbeddedHexPair(c, in)) != null) {</span><a href="#l397"></a>
<span id="l398"></span><a href="#l398"></a>
<span id="l399">                    // always encode AVAs with embedded hex as UTF8</span><a href="#l399"></a>
<span id="l400">                    isPrintableString = false;</span><a href="#l400"></a>
<span id="l401"></span><a href="#l401"></a>
<span id="l402">                    // append consecutive embedded hex</span><a href="#l402"></a>
<span id="l403">                    // as single string later</span><a href="#l403"></a>
<span id="l404">                    embeddedHex.add(hexByte);</span><a href="#l404"></a>
<span id="l405">                    c = in.read();</span><a href="#l405"></a>
<span id="l406">                    leadingChar = false;</span><a href="#l406"></a>
<span id="l407">                    continue;</span><a href="#l407"></a>
<span id="l408">                }</span><a href="#l408"></a>
<span id="l409"></span><a href="#l409"></a>
<span id="l410">                // check if character was improperly escaped</span><a href="#l410"></a>
<span id="l411">                if (format == DEFAULT &amp;&amp;</span><a href="#l411"></a>
<span id="l412">                       specialCharsDefault.indexOf((char)c) == -1) {</span><a href="#l412"></a>
<span id="l413">                    throw new IOException</span><a href="#l413"></a>
<span id="l414">                        (&quot;Invalid escaped character in AVA: '&quot; +</span><a href="#l414"></a>
<span id="l415">                        (char)c + &quot;'&quot;);</span><a href="#l415"></a>
<span id="l416">                } else if (format == RFC2253) {</span><a href="#l416"></a>
<span id="l417">                    if (c == ' ') {</span><a href="#l417"></a>
<span id="l418">                        // only leading/trailing space can be escaped</span><a href="#l418"></a>
<span id="l419">                        if (!leadingChar &amp;&amp; !trailingSpace(in)) {</span><a href="#l419"></a>
<span id="l420">                            throw new IOException</span><a href="#l420"></a>
<span id="l421">                                    (&quot;Invalid escaped space character &quot; +</span><a href="#l421"></a>
<span id="l422">                                    &quot;in AVA.  Only a leading or trailing &quot; +</span><a href="#l422"></a>
<span id="l423">                                    &quot;space character can be escaped.&quot;);</span><a href="#l423"></a>
<span id="l424">                        }</span><a href="#l424"></a>
<span id="l425">                    } else if (c == '#') {</span><a href="#l425"></a>
<span id="l426">                        // only leading '#' can be escaped</span><a href="#l426"></a>
<span id="l427">                        if (!leadingChar) {</span><a href="#l427"></a>
<span id="l428">                            throw new IOException</span><a href="#l428"></a>
<span id="l429">                                (&quot;Invalid escaped '#' character in AVA.  &quot; +</span><a href="#l429"></a>
<span id="l430">                                &quot;Only a leading '#' can be escaped.&quot;);</span><a href="#l430"></a>
<span id="l431">                        }</span><a href="#l431"></a>
<span id="l432">                    } else if (specialChars2253.indexOf((char)c) == -1) {</span><a href="#l432"></a>
<span id="l433">                        throw new IOException</span><a href="#l433"></a>
<span id="l434">                                (&quot;Invalid escaped character in AVA: '&quot; +</span><a href="#l434"></a>
<span id="l435">                                (char)c + &quot;'&quot;);</span><a href="#l435"></a>
<span id="l436">                    }</span><a href="#l436"></a>
<span id="l437">                }</span><a href="#l437"></a>
<span id="l438">            } else {</span><a href="#l438"></a>
<span id="l439">                // check if character should have been escaped</span><a href="#l439"></a>
<span id="l440">                if (format == RFC2253) {</span><a href="#l440"></a>
<span id="l441">                    if (specialChars2253.indexOf((char)c) != -1) {</span><a href="#l441"></a>
<span id="l442">                        throw new IOException</span><a href="#l442"></a>
<span id="l443">                                (&quot;Character '&quot; + (char)c +</span><a href="#l443"></a>
<span id="l444">                                 &quot;' in AVA appears without escape&quot;);</span><a href="#l444"></a>
<span id="l445">                    }</span><a href="#l445"></a>
<span id="l446">                } else if (escapedDefault.indexOf((char)c) != -1) {</span><a href="#l446"></a>
<span id="l447">                    throw new IOException</span><a href="#l447"></a>
<span id="l448">                            (&quot;Character '&quot; + (char)c +</span><a href="#l448"></a>
<span id="l449">                            &quot;' in AVA appears without escape&quot;);</span><a href="#l449"></a>
<span id="l450">                }</span><a href="#l450"></a>
<span id="l451">            }</span><a href="#l451"></a>
<span id="l452"></span><a href="#l452"></a>
<span id="l453">            // add embedded hex bytes before next char</span><a href="#l453"></a>
<span id="l454">            if (embeddedHex.size() &gt; 0) {</span><a href="#l454"></a>
<span id="l455">                // add space(s) before embedded hex bytes</span><a href="#l455"></a>
<span id="l456">                for (int i = 0; i &lt; spaceCount; i++) {</span><a href="#l456"></a>
<span id="l457">                    temp.append(&quot; &quot;);</span><a href="#l457"></a>
<span id="l458">                }</span><a href="#l458"></a>
<span id="l459">                spaceCount = 0;</span><a href="#l459"></a>
<span id="l460"></span><a href="#l460"></a>
<span id="l461">                String hexString = getEmbeddedHexString(embeddedHex);</span><a href="#l461"></a>
<span id="l462">                temp.append(hexString);</span><a href="#l462"></a>
<span id="l463">                embeddedHex.clear();</span><a href="#l463"></a>
<span id="l464">            }</span><a href="#l464"></a>
<span id="l465"></span><a href="#l465"></a>
<span id="l466">            // check for non-PrintableString chars</span><a href="#l466"></a>
<span id="l467">            isPrintableString &amp;= DerValue.isPrintableStringChar((char)c);</span><a href="#l467"></a>
<span id="l468">            if (c == ' ' &amp;&amp; escape == false) {</span><a href="#l468"></a>
<span id="l469">                // do not add non-escaped spaces yet</span><a href="#l469"></a>
<span id="l470">                // (non-escaped trailing spaces are ignored)</span><a href="#l470"></a>
<span id="l471">                spaceCount++;</span><a href="#l471"></a>
<span id="l472">            } else {</span><a href="#l472"></a>
<span id="l473">                // add space(s)</span><a href="#l473"></a>
<span id="l474">                for (int i = 0; i &lt; spaceCount; i++) {</span><a href="#l474"></a>
<span id="l475">                    temp.append(&quot; &quot;);</span><a href="#l475"></a>
<span id="l476">                }</span><a href="#l476"></a>
<span id="l477">                spaceCount = 0;</span><a href="#l477"></a>
<span id="l478">                temp.append((char)c);</span><a href="#l478"></a>
<span id="l479">            }</span><a href="#l479"></a>
<span id="l480">            c = in.read();</span><a href="#l480"></a>
<span id="l481">            leadingChar = false;</span><a href="#l481"></a>
<span id="l482">        } while (isTerminator(c, format) == false);</span><a href="#l482"></a>
<span id="l483"></span><a href="#l483"></a>
<span id="l484">        if (format == RFC2253 &amp;&amp; spaceCount &gt; 0) {</span><a href="#l484"></a>
<span id="l485">            throw new IOException(&quot;Incorrect AVA RFC2253 format - &quot; +</span><a href="#l485"></a>
<span id="l486">                                        &quot;trailing space must be escaped&quot;);</span><a href="#l486"></a>
<span id="l487">        }</span><a href="#l487"></a>
<span id="l488"></span><a href="#l488"></a>
<span id="l489">        // add trailing embedded hex bytes</span><a href="#l489"></a>
<span id="l490">        if (embeddedHex.size() &gt; 0) {</span><a href="#l490"></a>
<span id="l491">            String hexString = getEmbeddedHexString(embeddedHex);</span><a href="#l491"></a>
<span id="l492">            temp.append(hexString);</span><a href="#l492"></a>
<span id="l493">            embeddedHex.clear();</span><a href="#l493"></a>
<span id="l494">        }</span><a href="#l494"></a>
<span id="l495"></span><a href="#l495"></a>
<span id="l496">        // encode as PrintableString unless value contains</span><a href="#l496"></a>
<span id="l497">        // non-PrintableString chars</span><a href="#l497"></a>
<span id="l498">        if (this.oid.equals((Object)PKCS9Attribute.EMAIL_ADDRESS_OID) ||</span><a href="#l498"></a>
<span id="l499">            (this.oid.equals((Object)X500Name.DOMAIN_COMPONENT_OID) &amp;&amp;</span><a href="#l499"></a>
<span id="l500">                PRESERVE_OLD_DC_ENCODING == false)) {</span><a href="#l500"></a>
<span id="l501">            // EmailAddress and DomainComponent must be IA5String</span><a href="#l501"></a>
<span id="l502">            return new DerValue(DerValue.tag_IA5String, temp.toString());</span><a href="#l502"></a>
<span id="l503">        } else if (isPrintableString) {</span><a href="#l503"></a>
<span id="l504">            return new DerValue(temp.toString());</span><a href="#l504"></a>
<span id="l505">        } else {</span><a href="#l505"></a>
<span id="l506">            return new DerValue(DerValue.tag_UTF8String, temp.toString());</span><a href="#l506"></a>
<span id="l507">        }</span><a href="#l507"></a>
<span id="l508">    }</span><a href="#l508"></a>
<span id="l509"></span><a href="#l509"></a>
<span id="l510">    private static Byte getEmbeddedHexPair(int c1, Reader in)</span><a href="#l510"></a>
<span id="l511">        throws IOException {</span><a href="#l511"></a>
<span id="l512"></span><a href="#l512"></a>
<span id="l513">        if (hexDigits.indexOf(Character.toUpperCase((char)c1)) &gt;= 0) {</span><a href="#l513"></a>
<span id="l514">            int c2 = readChar(in, &quot;unexpected EOF - &quot; +</span><a href="#l514"></a>
<span id="l515">                        &quot;escaped hex value must include two valid digits&quot;);</span><a href="#l515"></a>
<span id="l516"></span><a href="#l516"></a>
<span id="l517">            if (hexDigits.indexOf(Character.toUpperCase((char)c2)) &gt;= 0) {</span><a href="#l517"></a>
<span id="l518">                int hi = Character.digit((char)c1, 16);</span><a href="#l518"></a>
<span id="l519">                int lo = Character.digit((char)c2, 16);</span><a href="#l519"></a>
<span id="l520">                return new Byte((byte)((hi&lt;&lt;4) + lo));</span><a href="#l520"></a>
<span id="l521">            } else {</span><a href="#l521"></a>
<span id="l522">                throw new IOException</span><a href="#l522"></a>
<span id="l523">                        (&quot;escaped hex value must include two valid digits&quot;);</span><a href="#l523"></a>
<span id="l524">            }</span><a href="#l524"></a>
<span id="l525">        }</span><a href="#l525"></a>
<span id="l526">        return null;</span><a href="#l526"></a>
<span id="l527">    }</span><a href="#l527"></a>
<span id="l528"></span><a href="#l528"></a>
<span id="l529">    private static String getEmbeddedHexString(List&lt;Byte&gt; hexList)</span><a href="#l529"></a>
<span id="l530">                                                throws IOException {</span><a href="#l530"></a>
<span id="l531">        int n = hexList.size();</span><a href="#l531"></a>
<span id="l532">        byte[] hexBytes = new byte[n];</span><a href="#l532"></a>
<span id="l533">        for (int i = 0; i &lt; n; i++) {</span><a href="#l533"></a>
<span id="l534">                hexBytes[i] = hexList.get(i).byteValue();</span><a href="#l534"></a>
<span id="l535">        }</span><a href="#l535"></a>
<span id="l536">        return new String(hexBytes, &quot;UTF8&quot;);</span><a href="#l536"></a>
<span id="l537">    }</span><a href="#l537"></a>
<span id="l538"></span><a href="#l538"></a>
<span id="l539">    private static boolean isTerminator(int ch, int format) {</span><a href="#l539"></a>
<span id="l540">        switch (ch) {</span><a href="#l540"></a>
<span id="l541">        case -1:</span><a href="#l541"></a>
<span id="l542">        case '+':</span><a href="#l542"></a>
<span id="l543">        case ',':</span><a href="#l543"></a>
<span id="l544">            return true;</span><a href="#l544"></a>
<span id="l545">        case ';':</span><a href="#l545"></a>
<span id="l546">            return format != RFC2253;</span><a href="#l546"></a>
<span id="l547">        default:</span><a href="#l547"></a>
<span id="l548">            return false;</span><a href="#l548"></a>
<span id="l549">        }</span><a href="#l549"></a>
<span id="l550">    }</span><a href="#l550"></a>
<span id="l551"></span><a href="#l551"></a>
<span id="l552">    private static int readChar(Reader in, String errMsg) throws IOException {</span><a href="#l552"></a>
<span id="l553">        int c = in.read();</span><a href="#l553"></a>
<span id="l554">        if (c == -1) {</span><a href="#l554"></a>
<span id="l555">            throw new IOException(errMsg);</span><a href="#l555"></a>
<span id="l556">        }</span><a href="#l556"></a>
<span id="l557">        return c;</span><a href="#l557"></a>
<span id="l558">    }</span><a href="#l558"></a>
<span id="l559"></span><a href="#l559"></a>
<span id="l560">    private static boolean trailingSpace(Reader in) throws IOException {</span><a href="#l560"></a>
<span id="l561"></span><a href="#l561"></a>
<span id="l562">        boolean trailing = false;</span><a href="#l562"></a>
<span id="l563"></span><a href="#l563"></a>
<span id="l564">        if (!in.markSupported()) {</span><a href="#l564"></a>
<span id="l565">            // oh well</span><a href="#l565"></a>
<span id="l566">            return true;</span><a href="#l566"></a>
<span id="l567">        } else {</span><a href="#l567"></a>
<span id="l568">            // make readAheadLimit huge -</span><a href="#l568"></a>
<span id="l569">            // in practice, AVA was passed a StringReader from X500Name,</span><a href="#l569"></a>
<span id="l570">            // and StringReader ignores readAheadLimit anyways</span><a href="#l570"></a>
<span id="l571">            in.mark(9999);</span><a href="#l571"></a>
<span id="l572">            while (true) {</span><a href="#l572"></a>
<span id="l573">                int nextChar = in.read();</span><a href="#l573"></a>
<span id="l574">                if (nextChar == -1) {</span><a href="#l574"></a>
<span id="l575">                    trailing = true;</span><a href="#l575"></a>
<span id="l576">                    break;</span><a href="#l576"></a>
<span id="l577">                } else if (nextChar == ' ') {</span><a href="#l577"></a>
<span id="l578">                    continue;</span><a href="#l578"></a>
<span id="l579">                } else if (nextChar == '\\') {</span><a href="#l579"></a>
<span id="l580">                    int followingChar = in.read();</span><a href="#l580"></a>
<span id="l581">                    if (followingChar != ' ') {</span><a href="#l581"></a>
<span id="l582">                        trailing = false;</span><a href="#l582"></a>
<span id="l583">                        break;</span><a href="#l583"></a>
<span id="l584">                    }</span><a href="#l584"></a>
<span id="l585">                } else {</span><a href="#l585"></a>
<span id="l586">                    trailing = false;</span><a href="#l586"></a>
<span id="l587">                    break;</span><a href="#l587"></a>
<span id="l588">                }</span><a href="#l588"></a>
<span id="l589">            }</span><a href="#l589"></a>
<span id="l590"></span><a href="#l590"></a>
<span id="l591">            in.reset();</span><a href="#l591"></a>
<span id="l592">            return trailing;</span><a href="#l592"></a>
<span id="l593">        }</span><a href="#l593"></a>
<span id="l594">    }</span><a href="#l594"></a>
<span id="l595"></span><a href="#l595"></a>
<span id="l596">    AVA(DerValue derval) throws IOException {</span><a href="#l596"></a>
<span id="l597">        // Individual attribute value assertions are SEQUENCE of two values.</span><a href="#l597"></a>
<span id="l598">        // That'd be a &quot;struct&quot; outside of ASN.1.</span><a href="#l598"></a>
<span id="l599">        if (derval.tag != DerValue.tag_Sequence) {</span><a href="#l599"></a>
<span id="l600">            throw new IOException(&quot;AVA not a sequence&quot;);</span><a href="#l600"></a>
<span id="l601">        }</span><a href="#l601"></a>
<span id="l602">        oid = derval.data.getOID();</span><a href="#l602"></a>
<span id="l603">        value = derval.data.getDerValue();</span><a href="#l603"></a>
<span id="l604"></span><a href="#l604"></a>
<span id="l605">        if (derval.data.available() != 0) {</span><a href="#l605"></a>
<span id="l606">            throw new IOException(&quot;AVA, extra bytes = &quot;</span><a href="#l606"></a>
<span id="l607">                + derval.data.available());</span><a href="#l607"></a>
<span id="l608">        }</span><a href="#l608"></a>
<span id="l609">    }</span><a href="#l609"></a>
<span id="l610"></span><a href="#l610"></a>
<span id="l611">    AVA(DerInputStream in) throws IOException {</span><a href="#l611"></a>
<span id="l612">        this(in.getDerValue());</span><a href="#l612"></a>
<span id="l613">    }</span><a href="#l613"></a>
<span id="l614"></span><a href="#l614"></a>
<span id="l615">    public boolean equals(Object obj) {</span><a href="#l615"></a>
<span id="l616">        if (this == obj) {</span><a href="#l616"></a>
<span id="l617">            return true;</span><a href="#l617"></a>
<span id="l618">        }</span><a href="#l618"></a>
<span id="l619">        if (obj instanceof AVA == false) {</span><a href="#l619"></a>
<span id="l620">            return false;</span><a href="#l620"></a>
<span id="l621">        }</span><a href="#l621"></a>
<span id="l622">        AVA other = (AVA)obj;</span><a href="#l622"></a>
<span id="l623">        return this.toRFC2253CanonicalString().equals</span><a href="#l623"></a>
<span id="l624">                                (other.toRFC2253CanonicalString());</span><a href="#l624"></a>
<span id="l625">    }</span><a href="#l625"></a>
<span id="l626"></span><a href="#l626"></a>
<span id="l627">    /**</span><a href="#l627"></a>
<span id="l628">     * Returns a hashcode for this AVA.</span><a href="#l628"></a>
<span id="l629">     *</span><a href="#l629"></a>
<span id="l630">     * @return a hashcode for this AVA.</span><a href="#l630"></a>
<span id="l631">     */</span><a href="#l631"></a>
<span id="l632">    public int hashCode() {</span><a href="#l632"></a>
<span id="l633">        return toRFC2253CanonicalString().hashCode();</span><a href="#l633"></a>
<span id="l634">    }</span><a href="#l634"></a>
<span id="l635"></span><a href="#l635"></a>
<span id="l636">    /*</span><a href="#l636"></a>
<span id="l637">     * AVAs are encoded as a SEQUENCE of two elements.</span><a href="#l637"></a>
<span id="l638">     */</span><a href="#l638"></a>
<span id="l639">    public void encode(DerOutputStream out) throws IOException {</span><a href="#l639"></a>
<span id="l640">        derEncode(out);</span><a href="#l640"></a>
<span id="l641">    }</span><a href="#l641"></a>
<span id="l642"></span><a href="#l642"></a>
<span id="l643">    /**</span><a href="#l643"></a>
<span id="l644">     * DER encode this object onto an output stream.</span><a href="#l644"></a>
<span id="l645">     * Implements the &lt;code&gt;DerEncoder&lt;/code&gt; interface.</span><a href="#l645"></a>
<span id="l646">     *</span><a href="#l646"></a>
<span id="l647">     * @param out</span><a href="#l647"></a>
<span id="l648">     * the output stream on which to write the DER encoding.</span><a href="#l648"></a>
<span id="l649">     *</span><a href="#l649"></a>
<span id="l650">     * @exception IOException on encoding error.</span><a href="#l650"></a>
<span id="l651">     */</span><a href="#l651"></a>
<span id="l652">    public void derEncode(OutputStream out) throws IOException {</span><a href="#l652"></a>
<span id="l653">        DerOutputStream         tmp = new DerOutputStream();</span><a href="#l653"></a>
<span id="l654">        DerOutputStream         tmp2 = new DerOutputStream();</span><a href="#l654"></a>
<span id="l655"></span><a href="#l655"></a>
<span id="l656">        tmp.putOID(oid);</span><a href="#l656"></a>
<span id="l657">        value.encode(tmp);</span><a href="#l657"></a>
<span id="l658">        tmp2.write(DerValue.tag_Sequence, tmp);</span><a href="#l658"></a>
<span id="l659">        out.write(tmp2.toByteArray());</span><a href="#l659"></a>
<span id="l660">    }</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">    private String toKeyword(int format, Map&lt;String, String&gt; oidMap) {</span><a href="#l662"></a>
<span id="l663">        return AVAKeyword.getKeyword(oid, format, oidMap);</span><a href="#l663"></a>
<span id="l664">    }</span><a href="#l664"></a>
<span id="l665"></span><a href="#l665"></a>
<span id="l666">    /**</span><a href="#l666"></a>
<span id="l667">     * Returns a printable form of this attribute, using RFC 1779</span><a href="#l667"></a>
<span id="l668">     * syntax for individual attribute/value assertions.</span><a href="#l668"></a>
<span id="l669">     */</span><a href="#l669"></a>
<span id="l670">    public String toString() {</span><a href="#l670"></a>
<span id="l671">        return toKeywordValueString</span><a href="#l671"></a>
<span id="l672">            (toKeyword(DEFAULT, Collections.&lt;String, String&gt;emptyMap()));</span><a href="#l672"></a>
<span id="l673">    }</span><a href="#l673"></a>
<span id="l674"></span><a href="#l674"></a>
<span id="l675">    /**</span><a href="#l675"></a>
<span id="l676">     * Returns a printable form of this attribute, using RFC 1779</span><a href="#l676"></a>
<span id="l677">     * syntax for individual attribute/value assertions. It only</span><a href="#l677"></a>
<span id="l678">     * emits standardised keywords.</span><a href="#l678"></a>
<span id="l679">     */</span><a href="#l679"></a>
<span id="l680">    public String toRFC1779String() {</span><a href="#l680"></a>
<span id="l681">        return toRFC1779String(Collections.&lt;String, String&gt;emptyMap());</span><a href="#l681"></a>
<span id="l682">    }</span><a href="#l682"></a>
<span id="l683"></span><a href="#l683"></a>
<span id="l684">    /**</span><a href="#l684"></a>
<span id="l685">     * Returns a printable form of this attribute, using RFC 1779</span><a href="#l685"></a>
<span id="l686">     * syntax for individual attribute/value assertions. It</span><a href="#l686"></a>
<span id="l687">     * emits standardised keywords, as well as keywords contained in the</span><a href="#l687"></a>
<span id="l688">     * OID/keyword map.</span><a href="#l688"></a>
<span id="l689">     */</span><a href="#l689"></a>
<span id="l690">    public String toRFC1779String(Map&lt;String, String&gt; oidMap) {</span><a href="#l690"></a>
<span id="l691">        return toKeywordValueString(toKeyword(RFC1779, oidMap));</span><a href="#l691"></a>
<span id="l692">    }</span><a href="#l692"></a>
<span id="l693"></span><a href="#l693"></a>
<span id="l694">    /**</span><a href="#l694"></a>
<span id="l695">     * Returns a printable form of this attribute, using RFC 2253</span><a href="#l695"></a>
<span id="l696">     * syntax for individual attribute/value assertions. It only</span><a href="#l696"></a>
<span id="l697">     * emits standardised keywords.</span><a href="#l697"></a>
<span id="l698">     */</span><a href="#l698"></a>
<span id="l699">    public String toRFC2253String() {</span><a href="#l699"></a>
<span id="l700">        return toRFC2253String(Collections.&lt;String, String&gt;emptyMap());</span><a href="#l700"></a>
<span id="l701">    }</span><a href="#l701"></a>
<span id="l702"></span><a href="#l702"></a>
<span id="l703">    /**</span><a href="#l703"></a>
<span id="l704">     * Returns a printable form of this attribute, using RFC 2253</span><a href="#l704"></a>
<span id="l705">     * syntax for individual attribute/value assertions. It</span><a href="#l705"></a>
<span id="l706">     * emits standardised keywords, as well as keywords contained in the</span><a href="#l706"></a>
<span id="l707">     * OID/keyword map.</span><a href="#l707"></a>
<span id="l708">     */</span><a href="#l708"></a>
<span id="l709">    public String toRFC2253String(Map&lt;String, String&gt; oidMap) {</span><a href="#l709"></a>
<span id="l710">        /*</span><a href="#l710"></a>
<span id="l711">         * Section 2.3: The AttributeTypeAndValue is encoded as the string</span><a href="#l711"></a>
<span id="l712">         * representation of the AttributeType, followed by an equals character</span><a href="#l712"></a>
<span id="l713">         * ('=' ASCII 61), followed by the string representation of the</span><a href="#l713"></a>
<span id="l714">         * AttributeValue. The encoding of the AttributeValue is given in</span><a href="#l714"></a>
<span id="l715">         * section 2.4.</span><a href="#l715"></a>
<span id="l716">         */</span><a href="#l716"></a>
<span id="l717">        StringBuilder typeAndValue = new StringBuilder(100);</span><a href="#l717"></a>
<span id="l718">        typeAndValue.append(toKeyword(RFC2253, oidMap));</span><a href="#l718"></a>
<span id="l719">        typeAndValue.append('=');</span><a href="#l719"></a>
<span id="l720"></span><a href="#l720"></a>
<span id="l721">        /*</span><a href="#l721"></a>
<span id="l722">         * Section 2.4: Converting an AttributeValue from ASN.1 to a String.</span><a href="#l722"></a>
<span id="l723">         * If the AttributeValue is of a type which does not have a string</span><a href="#l723"></a>
<span id="l724">         * representation defined for it, then it is simply encoded as an</span><a href="#l724"></a>
<span id="l725">         * octothorpe character ('#' ASCII 35) followed by the hexadecimal</span><a href="#l725"></a>
<span id="l726">         * representation of each of the bytes of the BER encoding of the X.500</span><a href="#l726"></a>
<span id="l727">         * AttributeValue.  This form SHOULD be used if the AttributeType is of</span><a href="#l727"></a>
<span id="l728">         * the dotted-decimal form.</span><a href="#l728"></a>
<span id="l729">         */</span><a href="#l729"></a>
<span id="l730">        if ((typeAndValue.charAt(0) &gt;= '0' &amp;&amp; typeAndValue.charAt(0) &lt;= '9') ||</span><a href="#l730"></a>
<span id="l731">            !isDerString(value, false))</span><a href="#l731"></a>
<span id="l732">        {</span><a href="#l732"></a>
<span id="l733">            byte[] data = null;</span><a href="#l733"></a>
<span id="l734">            try {</span><a href="#l734"></a>
<span id="l735">                data = value.toByteArray();</span><a href="#l735"></a>
<span id="l736">            } catch (IOException ie) {</span><a href="#l736"></a>
<span id="l737">                throw new IllegalArgumentException(&quot;DER Value conversion&quot;);</span><a href="#l737"></a>
<span id="l738">            }</span><a href="#l738"></a>
<span id="l739">            typeAndValue.append('#');</span><a href="#l739"></a>
<span id="l740">            for (int j = 0; j &lt; data.length; j++) {</span><a href="#l740"></a>
<span id="l741">                byte b = data[j];</span><a href="#l741"></a>
<span id="l742">                typeAndValue.append(Character.forDigit(0xF &amp; (b &gt;&gt;&gt; 4), 16));</span><a href="#l742"></a>
<span id="l743">                typeAndValue.append(Character.forDigit(0xF &amp; b, 16));</span><a href="#l743"></a>
<span id="l744">            }</span><a href="#l744"></a>
<span id="l745">        } else {</span><a href="#l745"></a>
<span id="l746">            /*</span><a href="#l746"></a>
<span id="l747">             * 2.4 (cont): Otherwise, if the AttributeValue is of a type which</span><a href="#l747"></a>
<span id="l748">             * has a string representation, the value is converted first to a</span><a href="#l748"></a>
<span id="l749">             * UTF-8 string according to its syntax specification.</span><a href="#l749"></a>
<span id="l750">             *</span><a href="#l750"></a>
<span id="l751">             * NOTE: this implementation only emits DirectoryStrings of the</span><a href="#l751"></a>
<span id="l752">             * types returned by isDerString().</span><a href="#l752"></a>
<span id="l753">             */</span><a href="#l753"></a>
<span id="l754">            String valStr = null;</span><a href="#l754"></a>
<span id="l755">            try {</span><a href="#l755"></a>
<span id="l756">                valStr = new String(value.getDataBytes(), &quot;UTF8&quot;);</span><a href="#l756"></a>
<span id="l757">            } catch (IOException ie) {</span><a href="#l757"></a>
<span id="l758">                throw new IllegalArgumentException(&quot;DER Value conversion&quot;);</span><a href="#l758"></a>
<span id="l759">            }</span><a href="#l759"></a>
<span id="l760"></span><a href="#l760"></a>
<span id="l761">            /*</span><a href="#l761"></a>
<span id="l762">             * 2.4 (cont): If the UTF-8 string does not have any of the</span><a href="#l762"></a>
<span id="l763">             * following characters which need escaping, then that string can be</span><a href="#l763"></a>
<span id="l764">             * used as the string representation of the value.</span><a href="#l764"></a>
<span id="l765">             *</span><a href="#l765"></a>
<span id="l766">             *   o   a space or &quot;#&quot; character occurring at the beginning of the</span><a href="#l766"></a>
<span id="l767">             *       string</span><a href="#l767"></a>
<span id="l768">             *   o   a space character occurring at the end of the string</span><a href="#l768"></a>
<span id="l769">             *   o   one of the characters &quot;,&quot;, &quot;+&quot;, &quot;&quot;&quot;, &quot;\&quot;, &quot;&lt;&quot;, &quot;&gt;&quot; or &quot;;&quot;</span><a href="#l769"></a>
<span id="l770">             *</span><a href="#l770"></a>
<span id="l771">             * Implementations MAY escape other characters.</span><a href="#l771"></a>
<span id="l772">             *</span><a href="#l772"></a>
<span id="l773">             * NOTE: this implementation also recognizes &quot;=&quot; and &quot;#&quot; as</span><a href="#l773"></a>
<span id="l774">             * characters which need escaping, and null which is escaped as</span><a href="#l774"></a>
<span id="l775">             * '\00' (see RFC 4514).</span><a href="#l775"></a>
<span id="l776">             *</span><a href="#l776"></a>
<span id="l777">             * If a character to be escaped is one of the list shown above, then</span><a href="#l777"></a>
<span id="l778">             * it is prefixed by a backslash ('\' ASCII 92).</span><a href="#l778"></a>
<span id="l779">             *</span><a href="#l779"></a>
<span id="l780">             * Otherwise the character to be escaped is replaced by a backslash</span><a href="#l780"></a>
<span id="l781">             * and two hex digits, which form a single byte in the code of the</span><a href="#l781"></a>
<span id="l782">             * character.</span><a href="#l782"></a>
<span id="l783">             */</span><a href="#l783"></a>
<span id="l784">            final String escapees = &quot;,=+&lt;&gt;#;\&quot;\\&quot;;</span><a href="#l784"></a>
<span id="l785">            StringBuilder sbuffer = new StringBuilder();</span><a href="#l785"></a>
<span id="l786"></span><a href="#l786"></a>
<span id="l787">            for (int i = 0; i &lt; valStr.length(); i++) {</span><a href="#l787"></a>
<span id="l788">                char c = valStr.charAt(i);</span><a href="#l788"></a>
<span id="l789">                if (DerValue.isPrintableStringChar(c) ||</span><a href="#l789"></a>
<span id="l790">                    escapees.indexOf(c) &gt;= 0) {</span><a href="#l790"></a>
<span id="l791"></span><a href="#l791"></a>
<span id="l792">                    // escape escapees</span><a href="#l792"></a>
<span id="l793">                    if (escapees.indexOf(c) &gt;= 0) {</span><a href="#l793"></a>
<span id="l794">                        sbuffer.append('\\');</span><a href="#l794"></a>
<span id="l795">                    }</span><a href="#l795"></a>
<span id="l796"></span><a href="#l796"></a>
<span id="l797">                    // append printable/escaped char</span><a href="#l797"></a>
<span id="l798">                    sbuffer.append(c);</span><a href="#l798"></a>
<span id="l799"></span><a href="#l799"></a>
<span id="l800">                } else if (c == '\u0000') {</span><a href="#l800"></a>
<span id="l801">                    // escape null character</span><a href="#l801"></a>
<span id="l802">                    sbuffer.append(&quot;\\00&quot;);</span><a href="#l802"></a>
<span id="l803"></span><a href="#l803"></a>
<span id="l804">                } else if (debug != null &amp;&amp; Debug.isOn(&quot;ava&quot;)) {</span><a href="#l804"></a>
<span id="l805"></span><a href="#l805"></a>
<span id="l806">                    // embed non-printable/non-escaped char</span><a href="#l806"></a>
<span id="l807">                    // as escaped hex pairs for debugging</span><a href="#l807"></a>
<span id="l808">                    byte[] valueBytes = null;</span><a href="#l808"></a>
<span id="l809">                    try {</span><a href="#l809"></a>
<span id="l810">                        valueBytes = Character.toString(c).getBytes(&quot;UTF8&quot;);</span><a href="#l810"></a>
<span id="l811">                    } catch (IOException ie) {</span><a href="#l811"></a>
<span id="l812">                        throw new IllegalArgumentException</span><a href="#l812"></a>
<span id="l813">                                        (&quot;DER Value conversion&quot;);</span><a href="#l813"></a>
<span id="l814">                    }</span><a href="#l814"></a>
<span id="l815">                    for (int j = 0; j &lt; valueBytes.length; j++) {</span><a href="#l815"></a>
<span id="l816">                        sbuffer.append('\\');</span><a href="#l816"></a>
<span id="l817">                        char hexChar = Character.forDigit</span><a href="#l817"></a>
<span id="l818">                                (0xF &amp; (valueBytes[j] &gt;&gt;&gt; 4), 16);</span><a href="#l818"></a>
<span id="l819">                        sbuffer.append(Character.toUpperCase(hexChar));</span><a href="#l819"></a>
<span id="l820">                        hexChar = Character.forDigit</span><a href="#l820"></a>
<span id="l821">                                (0xF &amp; (valueBytes[j]), 16);</span><a href="#l821"></a>
<span id="l822">                        sbuffer.append(Character.toUpperCase(hexChar));</span><a href="#l822"></a>
<span id="l823">                    }</span><a href="#l823"></a>
<span id="l824">                } else {</span><a href="#l824"></a>
<span id="l825"></span><a href="#l825"></a>
<span id="l826">                    // append non-printable/non-escaped char</span><a href="#l826"></a>
<span id="l827">                    sbuffer.append(c);</span><a href="#l827"></a>
<span id="l828">                }</span><a href="#l828"></a>
<span id="l829">            }</span><a href="#l829"></a>
<span id="l830"></span><a href="#l830"></a>
<span id="l831">            char[] chars = sbuffer.toString().toCharArray();</span><a href="#l831"></a>
<span id="l832">            sbuffer = new StringBuilder();</span><a href="#l832"></a>
<span id="l833"></span><a href="#l833"></a>
<span id="l834">            // Find leading and trailing whitespace.</span><a href="#l834"></a>
<span id="l835">            int lead;   // index of first char that is not leading whitespace</span><a href="#l835"></a>
<span id="l836">            for (lead = 0; lead &lt; chars.length; lead++) {</span><a href="#l836"></a>
<span id="l837">                if (chars[lead] != ' ' &amp;&amp; chars[lead] != '\r') {</span><a href="#l837"></a>
<span id="l838">                    break;</span><a href="#l838"></a>
<span id="l839">                }</span><a href="#l839"></a>
<span id="l840">            }</span><a href="#l840"></a>
<span id="l841">            int trail;  // index of last char that is not trailing whitespace</span><a href="#l841"></a>
<span id="l842">            for (trail = chars.length - 1; trail &gt;= 0; trail--) {</span><a href="#l842"></a>
<span id="l843">                if (chars[trail] != ' ' &amp;&amp; chars[trail] != '\r') {</span><a href="#l843"></a>
<span id="l844">                    break;</span><a href="#l844"></a>
<span id="l845">                }</span><a href="#l845"></a>
<span id="l846">            }</span><a href="#l846"></a>
<span id="l847"></span><a href="#l847"></a>
<span id="l848">            // escape leading and trailing whitespace</span><a href="#l848"></a>
<span id="l849">            for (int i = 0; i &lt; chars.length; i++) {</span><a href="#l849"></a>
<span id="l850">                char c = chars[i];</span><a href="#l850"></a>
<span id="l851">                if (i &lt; lead || i &gt; trail) {</span><a href="#l851"></a>
<span id="l852">                    sbuffer.append('\\');</span><a href="#l852"></a>
<span id="l853">                }</span><a href="#l853"></a>
<span id="l854">                sbuffer.append(c);</span><a href="#l854"></a>
<span id="l855">            }</span><a href="#l855"></a>
<span id="l856">            typeAndValue.append(sbuffer.toString());</span><a href="#l856"></a>
<span id="l857">        }</span><a href="#l857"></a>
<span id="l858">        return typeAndValue.toString();</span><a href="#l858"></a>
<span id="l859">    }</span><a href="#l859"></a>
<span id="l860"></span><a href="#l860"></a>
<span id="l861">    public String toRFC2253CanonicalString() {</span><a href="#l861"></a>
<span id="l862">        /*</span><a href="#l862"></a>
<span id="l863">         * Section 2.3: The AttributeTypeAndValue is encoded as the string</span><a href="#l863"></a>
<span id="l864">         * representation of the AttributeType, followed by an equals character</span><a href="#l864"></a>
<span id="l865">         * ('=' ASCII 61), followed by the string representation of the</span><a href="#l865"></a>
<span id="l866">         * AttributeValue. The encoding of the AttributeValue is given in</span><a href="#l866"></a>
<span id="l867">         * section 2.4.</span><a href="#l867"></a>
<span id="l868">         */</span><a href="#l868"></a>
<span id="l869">        StringBuilder typeAndValue = new StringBuilder(40);</span><a href="#l869"></a>
<span id="l870">        typeAndValue.append</span><a href="#l870"></a>
<span id="l871">            (toKeyword(RFC2253, Collections.&lt;String, String&gt;emptyMap()));</span><a href="#l871"></a>
<span id="l872">        typeAndValue.append('=');</span><a href="#l872"></a>
<span id="l873"></span><a href="#l873"></a>
<span id="l874">        /*</span><a href="#l874"></a>
<span id="l875">         * Section 2.4: Converting an AttributeValue from ASN.1 to a String.</span><a href="#l875"></a>
<span id="l876">         * If the AttributeValue is of a type which does not have a string</span><a href="#l876"></a>
<span id="l877">         * representation defined for it, then it is simply encoded as an</span><a href="#l877"></a>
<span id="l878">         * octothorpe character ('#' ASCII 35) followed by the hexadecimal</span><a href="#l878"></a>
<span id="l879">         * representation of each of the bytes of the BER encoding of the X.500</span><a href="#l879"></a>
<span id="l880">         * AttributeValue.  This form SHOULD be used if the AttributeType is of</span><a href="#l880"></a>
<span id="l881">         * the dotted-decimal form.</span><a href="#l881"></a>
<span id="l882">         */</span><a href="#l882"></a>
<span id="l883">        if ((typeAndValue.charAt(0) &gt;= '0' &amp;&amp; typeAndValue.charAt(0) &lt;= '9') ||</span><a href="#l883"></a>
<span id="l884">            !isDerString(value, true))</span><a href="#l884"></a>
<span id="l885">        {</span><a href="#l885"></a>
<span id="l886">            byte[] data = null;</span><a href="#l886"></a>
<span id="l887">            try {</span><a href="#l887"></a>
<span id="l888">                data = value.toByteArray();</span><a href="#l888"></a>
<span id="l889">            } catch (IOException ie) {</span><a href="#l889"></a>
<span id="l890">                throw new IllegalArgumentException(&quot;DER Value conversion&quot;);</span><a href="#l890"></a>
<span id="l891">            }</span><a href="#l891"></a>
<span id="l892">            typeAndValue.append('#');</span><a href="#l892"></a>
<span id="l893">            for (int j = 0; j &lt; data.length; j++) {</span><a href="#l893"></a>
<span id="l894">                byte b = data[j];</span><a href="#l894"></a>
<span id="l895">                typeAndValue.append(Character.forDigit(0xF &amp; (b &gt;&gt;&gt; 4), 16));</span><a href="#l895"></a>
<span id="l896">                typeAndValue.append(Character.forDigit(0xF &amp; b, 16));</span><a href="#l896"></a>
<span id="l897">            }</span><a href="#l897"></a>
<span id="l898">        } else {</span><a href="#l898"></a>
<span id="l899">            /*</span><a href="#l899"></a>
<span id="l900">             * 2.4 (cont): Otherwise, if the AttributeValue is of a type which</span><a href="#l900"></a>
<span id="l901">             * has a string representation, the value is converted first to a</span><a href="#l901"></a>
<span id="l902">             * UTF-8 string according to its syntax specification.</span><a href="#l902"></a>
<span id="l903">             *</span><a href="#l903"></a>
<span id="l904">             * NOTE: this implementation only emits DirectoryStrings of the</span><a href="#l904"></a>
<span id="l905">             * types returned by isDerString().</span><a href="#l905"></a>
<span id="l906">             */</span><a href="#l906"></a>
<span id="l907">            String valStr = null;</span><a href="#l907"></a>
<span id="l908">            try {</span><a href="#l908"></a>
<span id="l909">                valStr = new String(value.getDataBytes(), &quot;UTF8&quot;);</span><a href="#l909"></a>
<span id="l910">            } catch (IOException ie) {</span><a href="#l910"></a>
<span id="l911">                throw new IllegalArgumentException(&quot;DER Value conversion&quot;);</span><a href="#l911"></a>
<span id="l912">            }</span><a href="#l912"></a>
<span id="l913"></span><a href="#l913"></a>
<span id="l914">            /*</span><a href="#l914"></a>
<span id="l915">             * 2.4 (cont): If the UTF-8 string does not have any of the</span><a href="#l915"></a>
<span id="l916">             * following characters which need escaping, then that string can be</span><a href="#l916"></a>
<span id="l917">             * used as the string representation of the value.</span><a href="#l917"></a>
<span id="l918">             *</span><a href="#l918"></a>
<span id="l919">             *   o   a space or &quot;#&quot; character occurring at the beginning of the</span><a href="#l919"></a>
<span id="l920">             *       string</span><a href="#l920"></a>
<span id="l921">             *   o   a space character occurring at the end of the string</span><a href="#l921"></a>
<span id="l922">             *</span><a href="#l922"></a>
<span id="l923">             *   o   one of the characters &quot;,&quot;, &quot;+&quot;, &quot;&quot;&quot;, &quot;\&quot;, &quot;&lt;&quot;, &quot;&gt;&quot; or &quot;;&quot;</span><a href="#l923"></a>
<span id="l924">             *</span><a href="#l924"></a>
<span id="l925">             * If a character to be escaped is one of the list shown above, then</span><a href="#l925"></a>
<span id="l926">             * it is prefixed by a backslash ('\' ASCII 92).</span><a href="#l926"></a>
<span id="l927">             *</span><a href="#l927"></a>
<span id="l928">             * Otherwise the character to be escaped is replaced by a backslash</span><a href="#l928"></a>
<span id="l929">             * and two hex digits, which form a single byte in the code of the</span><a href="#l929"></a>
<span id="l930">             * character.</span><a href="#l930"></a>
<span id="l931">             */</span><a href="#l931"></a>
<span id="l932">            final String escapees = &quot;,+&lt;&gt;;\&quot;\\&quot;;</span><a href="#l932"></a>
<span id="l933">            StringBuilder sbuffer = new StringBuilder();</span><a href="#l933"></a>
<span id="l934">            boolean previousWhite = false;</span><a href="#l934"></a>
<span id="l935"></span><a href="#l935"></a>
<span id="l936">            for (int i = 0; i &lt; valStr.length(); i++) {</span><a href="#l936"></a>
<span id="l937">                char c = valStr.charAt(i);</span><a href="#l937"></a>
<span id="l938"></span><a href="#l938"></a>
<span id="l939">                if (DerValue.isPrintableStringChar(c) ||</span><a href="#l939"></a>
<span id="l940">                    escapees.indexOf(c) &gt;= 0 ||</span><a href="#l940"></a>
<span id="l941">                    (i == 0 &amp;&amp; c == '#')) {</span><a href="#l941"></a>
<span id="l942"></span><a href="#l942"></a>
<span id="l943">                    // escape leading '#' and escapees</span><a href="#l943"></a>
<span id="l944">                    if ((i == 0 &amp;&amp; c == '#') || escapees.indexOf(c) &gt;= 0) {</span><a href="#l944"></a>
<span id="l945">                        sbuffer.append('\\');</span><a href="#l945"></a>
<span id="l946">                    }</span><a href="#l946"></a>
<span id="l947"></span><a href="#l947"></a>
<span id="l948">                    // convert multiple whitespace to single whitespace</span><a href="#l948"></a>
<span id="l949">                    if (!Character.isWhitespace(c)) {</span><a href="#l949"></a>
<span id="l950">                        previousWhite = false;</span><a href="#l950"></a>
<span id="l951">                        sbuffer.append(c);</span><a href="#l951"></a>
<span id="l952">                    } else {</span><a href="#l952"></a>
<span id="l953">                        if (previousWhite == false) {</span><a href="#l953"></a>
<span id="l954">                            // add single whitespace</span><a href="#l954"></a>
<span id="l955">                            previousWhite = true;</span><a href="#l955"></a>
<span id="l956">                            sbuffer.append(c);</span><a href="#l956"></a>
<span id="l957">                        } else {</span><a href="#l957"></a>
<span id="l958">                            // ignore subsequent consecutive whitespace</span><a href="#l958"></a>
<span id="l959">                            continue;</span><a href="#l959"></a>
<span id="l960">                        }</span><a href="#l960"></a>
<span id="l961">                    }</span><a href="#l961"></a>
<span id="l962"></span><a href="#l962"></a>
<span id="l963">                } else if (debug != null &amp;&amp; Debug.isOn(&quot;ava&quot;)) {</span><a href="#l963"></a>
<span id="l964"></span><a href="#l964"></a>
<span id="l965">                    // embed non-printable/non-escaped char</span><a href="#l965"></a>
<span id="l966">                    // as escaped hex pairs for debugging</span><a href="#l966"></a>
<span id="l967"></span><a href="#l967"></a>
<span id="l968">                    previousWhite = false;</span><a href="#l968"></a>
<span id="l969"></span><a href="#l969"></a>
<span id="l970">                    byte valueBytes[] = null;</span><a href="#l970"></a>
<span id="l971">                    try {</span><a href="#l971"></a>
<span id="l972">                        valueBytes = Character.toString(c).getBytes(&quot;UTF8&quot;);</span><a href="#l972"></a>
<span id="l973">                    } catch (IOException ie) {</span><a href="#l973"></a>
<span id="l974">                        throw new IllegalArgumentException</span><a href="#l974"></a>
<span id="l975">                                        (&quot;DER Value conversion&quot;);</span><a href="#l975"></a>
<span id="l976">                    }</span><a href="#l976"></a>
<span id="l977">                    for (int j = 0; j &lt; valueBytes.length; j++) {</span><a href="#l977"></a>
<span id="l978">                        sbuffer.append('\\');</span><a href="#l978"></a>
<span id="l979">                        sbuffer.append(Character.forDigit</span><a href="#l979"></a>
<span id="l980">                                        (0xF &amp; (valueBytes[j] &gt;&gt;&gt; 4), 16));</span><a href="#l980"></a>
<span id="l981">                        sbuffer.append(Character.forDigit</span><a href="#l981"></a>
<span id="l982">                                        (0xF &amp; (valueBytes[j]), 16));</span><a href="#l982"></a>
<span id="l983">                    }</span><a href="#l983"></a>
<span id="l984">                } else {</span><a href="#l984"></a>
<span id="l985"></span><a href="#l985"></a>
<span id="l986">                    // append non-printable/non-escaped char</span><a href="#l986"></a>
<span id="l987"></span><a href="#l987"></a>
<span id="l988">                    previousWhite = false;</span><a href="#l988"></a>
<span id="l989">                    sbuffer.append(c);</span><a href="#l989"></a>
<span id="l990">                }</span><a href="#l990"></a>
<span id="l991">            }</span><a href="#l991"></a>
<span id="l992"></span><a href="#l992"></a>
<span id="l993">            // remove leading and trailing whitespace from value</span><a href="#l993"></a>
<span id="l994">            typeAndValue.append(sbuffer.toString().trim());</span><a href="#l994"></a>
<span id="l995">        }</span><a href="#l995"></a>
<span id="l996"></span><a href="#l996"></a>
<span id="l997">        String canon = typeAndValue.toString();</span><a href="#l997"></a>
<span id="l998">        canon = canon.toUpperCase(Locale.US).toLowerCase(Locale.US);</span><a href="#l998"></a>
<span id="l999">        return Normalizer.normalize(canon, Normalizer.Form.NFKD);</span><a href="#l999"></a>
<span id="l1000">    }</span><a href="#l1000"></a>
<span id="l1001"></span><a href="#l1001"></a>
<span id="l1002">    /*</span><a href="#l1002"></a>
<span id="l1003">     * Return true if DerValue can be represented as a String.</span><a href="#l1003"></a>
<span id="l1004">     */</span><a href="#l1004"></a>
<span id="l1005">    private static boolean isDerString(DerValue value, boolean canonical) {</span><a href="#l1005"></a>
<span id="l1006">        if (canonical) {</span><a href="#l1006"></a>
<span id="l1007">            switch (value.tag) {</span><a href="#l1007"></a>
<span id="l1008">                case DerValue.tag_PrintableString:</span><a href="#l1008"></a>
<span id="l1009">                case DerValue.tag_UTF8String:</span><a href="#l1009"></a>
<span id="l1010">                    return true;</span><a href="#l1010"></a>
<span id="l1011">                default:</span><a href="#l1011"></a>
<span id="l1012">                    return false;</span><a href="#l1012"></a>
<span id="l1013">            }</span><a href="#l1013"></a>
<span id="l1014">        } else {</span><a href="#l1014"></a>
<span id="l1015">            switch (value.tag) {</span><a href="#l1015"></a>
<span id="l1016">                case DerValue.tag_PrintableString:</span><a href="#l1016"></a>
<span id="l1017">                case DerValue.tag_T61String:</span><a href="#l1017"></a>
<span id="l1018">                case DerValue.tag_IA5String:</span><a href="#l1018"></a>
<span id="l1019">                case DerValue.tag_GeneralString:</span><a href="#l1019"></a>
<span id="l1020">                case DerValue.tag_BMPString:</span><a href="#l1020"></a>
<span id="l1021">                case DerValue.tag_UTF8String:</span><a href="#l1021"></a>
<span id="l1022">                    return true;</span><a href="#l1022"></a>
<span id="l1023">                default:</span><a href="#l1023"></a>
<span id="l1024">                    return false;</span><a href="#l1024"></a>
<span id="l1025">            }</span><a href="#l1025"></a>
<span id="l1026">        }</span><a href="#l1026"></a>
<span id="l1027">    }</span><a href="#l1027"></a>
<span id="l1028"></span><a href="#l1028"></a>
<span id="l1029">    boolean hasRFC2253Keyword() {</span><a href="#l1029"></a>
<span id="l1030">        return AVAKeyword.hasKeyword(oid, RFC2253);</span><a href="#l1030"></a>
<span id="l1031">    }</span><a href="#l1031"></a>
<span id="l1032"></span><a href="#l1032"></a>
<span id="l1033">    private String toKeywordValueString(String keyword) {</span><a href="#l1033"></a>
<span id="l1034">        /*</span><a href="#l1034"></a>
<span id="l1035">         * Construct the value with as little copying and garbage</span><a href="#l1035"></a>
<span id="l1036">         * production as practical.  First the keyword (mandatory),</span><a href="#l1036"></a>
<span id="l1037">         * then the equals sign, finally the value.</span><a href="#l1037"></a>
<span id="l1038">         */</span><a href="#l1038"></a>
<span id="l1039">        StringBuilder   retval = new StringBuilder(40);</span><a href="#l1039"></a>
<span id="l1040"></span><a href="#l1040"></a>
<span id="l1041">        retval.append(keyword);</span><a href="#l1041"></a>
<span id="l1042">        retval.append(&quot;=&quot;);</span><a href="#l1042"></a>
<span id="l1043"></span><a href="#l1043"></a>
<span id="l1044">        try {</span><a href="#l1044"></a>
<span id="l1045">            String valStr = value.getAsString();</span><a href="#l1045"></a>
<span id="l1046"></span><a href="#l1046"></a>
<span id="l1047">            if (valStr == null) {</span><a href="#l1047"></a>
<span id="l1048"></span><a href="#l1048"></a>
<span id="l1049">                // rfc1779 specifies that attribute values associated</span><a href="#l1049"></a>
<span id="l1050">                // with non-standard keyword attributes may be represented</span><a href="#l1050"></a>
<span id="l1051">                // using the hex format below.  This will be used only</span><a href="#l1051"></a>
<span id="l1052">                // when the value is not a string type</span><a href="#l1052"></a>
<span id="l1053"></span><a href="#l1053"></a>
<span id="l1054">                byte    data [] = value.toByteArray();</span><a href="#l1054"></a>
<span id="l1055"></span><a href="#l1055"></a>
<span id="l1056">                retval.append('#');</span><a href="#l1056"></a>
<span id="l1057">                for (int i = 0; i &lt; data.length; i++) {</span><a href="#l1057"></a>
<span id="l1058">                    retval.append(hexDigits.charAt((data [i] &gt;&gt; 4) &amp; 0x0f));</span><a href="#l1058"></a>
<span id="l1059">                    retval.append(hexDigits.charAt(data [i] &amp; 0x0f));</span><a href="#l1059"></a>
<span id="l1060">                }</span><a href="#l1060"></a>
<span id="l1061"></span><a href="#l1061"></a>
<span id="l1062">            } else {</span><a href="#l1062"></a>
<span id="l1063"></span><a href="#l1063"></a>
<span id="l1064">                boolean quoteNeeded = false;</span><a href="#l1064"></a>
<span id="l1065">                StringBuilder sbuffer = new StringBuilder();</span><a href="#l1065"></a>
<span id="l1066">                boolean previousWhite = false;</span><a href="#l1066"></a>
<span id="l1067">                final String escapees = &quot;,+=\n&lt;&gt;#;\\\&quot;&quot;;</span><a href="#l1067"></a>
<span id="l1068"></span><a href="#l1068"></a>
<span id="l1069">                /*</span><a href="#l1069"></a>
<span id="l1070">                 * Special characters (e.g. AVA list separators) cause strings</span><a href="#l1070"></a>
<span id="l1071">                 * to need quoting, or at least escaping.  So do leading or</span><a href="#l1071"></a>
<span id="l1072">                 * trailing spaces, and multiple internal spaces.</span><a href="#l1072"></a>
<span id="l1073">                 */</span><a href="#l1073"></a>
<span id="l1074">                int length = valStr.length();</span><a href="#l1074"></a>
<span id="l1075">                boolean alreadyQuoted =</span><a href="#l1075"></a>
<span id="l1076">                    (length &gt; 1 &amp;&amp; valStr.charAt(0) == '\&quot;'</span><a href="#l1076"></a>
<span id="l1077">                     &amp;&amp; valStr.charAt(length - 1) == '\&quot;');</span><a href="#l1077"></a>
<span id="l1078"></span><a href="#l1078"></a>
<span id="l1079">                for (int i = 0; i &lt; length; i++) {</span><a href="#l1079"></a>
<span id="l1080">                    char c = valStr.charAt(i);</span><a href="#l1080"></a>
<span id="l1081">                    if (alreadyQuoted &amp;&amp; (i == 0 || i == length - 1)) {</span><a href="#l1081"></a>
<span id="l1082">                        sbuffer.append(c);</span><a href="#l1082"></a>
<span id="l1083">                        continue;</span><a href="#l1083"></a>
<span id="l1084">                    }</span><a href="#l1084"></a>
<span id="l1085">                    if (DerValue.isPrintableStringChar(c) ||</span><a href="#l1085"></a>
<span id="l1086">                        escapees.indexOf(c) &gt;= 0) {</span><a href="#l1086"></a>
<span id="l1087"></span><a href="#l1087"></a>
<span id="l1088">                        // quote if leading whitespace or special chars</span><a href="#l1088"></a>
<span id="l1089">                        if (!quoteNeeded &amp;&amp;</span><a href="#l1089"></a>
<span id="l1090">                            ((i == 0 &amp;&amp; (c == ' ' || c == '\n')) ||</span><a href="#l1090"></a>
<span id="l1091">                                escapees.indexOf(c) &gt;= 0)) {</span><a href="#l1091"></a>
<span id="l1092">                            quoteNeeded = true;</span><a href="#l1092"></a>
<span id="l1093">                        }</span><a href="#l1093"></a>
<span id="l1094"></span><a href="#l1094"></a>
<span id="l1095">                        // quote if multiple internal whitespace</span><a href="#l1095"></a>
<span id="l1096">                        if (!(c == ' ' || c == '\n')) {</span><a href="#l1096"></a>
<span id="l1097">                            // escape '&quot;' and '\'</span><a href="#l1097"></a>
<span id="l1098">                            if (c == '&quot;' || c == '\\') {</span><a href="#l1098"></a>
<span id="l1099">                                sbuffer.append('\\');</span><a href="#l1099"></a>
<span id="l1100">                            }</span><a href="#l1100"></a>
<span id="l1101">                            previousWhite = false;</span><a href="#l1101"></a>
<span id="l1102">                        } else {</span><a href="#l1102"></a>
<span id="l1103">                            if (!quoteNeeded &amp;&amp; previousWhite) {</span><a href="#l1103"></a>
<span id="l1104">                                quoteNeeded = true;</span><a href="#l1104"></a>
<span id="l1105">                            }</span><a href="#l1105"></a>
<span id="l1106">                            previousWhite = true;</span><a href="#l1106"></a>
<span id="l1107">                        }</span><a href="#l1107"></a>
<span id="l1108"></span><a href="#l1108"></a>
<span id="l1109">                        sbuffer.append(c);</span><a href="#l1109"></a>
<span id="l1110"></span><a href="#l1110"></a>
<span id="l1111">                    } else if (debug != null &amp;&amp; Debug.isOn(&quot;ava&quot;)) {</span><a href="#l1111"></a>
<span id="l1112"></span><a href="#l1112"></a>
<span id="l1113">                        // embed non-printable/non-escaped char</span><a href="#l1113"></a>
<span id="l1114">                        // as escaped hex pairs for debugging</span><a href="#l1114"></a>
<span id="l1115"></span><a href="#l1115"></a>
<span id="l1116">                        previousWhite = false;</span><a href="#l1116"></a>
<span id="l1117"></span><a href="#l1117"></a>
<span id="l1118">                        // embed escaped hex pairs</span><a href="#l1118"></a>
<span id="l1119">                        byte[] valueBytes =</span><a href="#l1119"></a>
<span id="l1120">                                Character.toString(c).getBytes(&quot;UTF8&quot;);</span><a href="#l1120"></a>
<span id="l1121">                        for (int j = 0; j &lt; valueBytes.length; j++) {</span><a href="#l1121"></a>
<span id="l1122">                            sbuffer.append('\\');</span><a href="#l1122"></a>
<span id="l1123">                            char hexChar = Character.forDigit</span><a href="#l1123"></a>
<span id="l1124">                                        (0xF &amp; (valueBytes[j] &gt;&gt;&gt; 4), 16);</span><a href="#l1124"></a>
<span id="l1125">                            sbuffer.append(Character.toUpperCase(hexChar));</span><a href="#l1125"></a>
<span id="l1126">                            hexChar = Character.forDigit</span><a href="#l1126"></a>
<span id="l1127">                                        (0xF &amp; (valueBytes[j]), 16);</span><a href="#l1127"></a>
<span id="l1128">                            sbuffer.append(Character.toUpperCase(hexChar));</span><a href="#l1128"></a>
<span id="l1129">                        }</span><a href="#l1129"></a>
<span id="l1130">                    } else {</span><a href="#l1130"></a>
<span id="l1131"></span><a href="#l1131"></a>
<span id="l1132">                        // append non-printable/non-escaped char</span><a href="#l1132"></a>
<span id="l1133"></span><a href="#l1133"></a>
<span id="l1134">                        previousWhite = false;</span><a href="#l1134"></a>
<span id="l1135">                        sbuffer.append(c);</span><a href="#l1135"></a>
<span id="l1136">                    }</span><a href="#l1136"></a>
<span id="l1137">                }</span><a href="#l1137"></a>
<span id="l1138"></span><a href="#l1138"></a>
<span id="l1139">                // quote if trailing whitespace</span><a href="#l1139"></a>
<span id="l1140">                if (sbuffer.length() &gt; 0) {</span><a href="#l1140"></a>
<span id="l1141">                    char trailChar = sbuffer.charAt(sbuffer.length() - 1);</span><a href="#l1141"></a>
<span id="l1142">                    if (trailChar == ' ' || trailChar == '\n') {</span><a href="#l1142"></a>
<span id="l1143">                        quoteNeeded = true;</span><a href="#l1143"></a>
<span id="l1144">                    }</span><a href="#l1144"></a>
<span id="l1145">                }</span><a href="#l1145"></a>
<span id="l1146"></span><a href="#l1146"></a>
<span id="l1147">                // Emit the string ... quote it if needed</span><a href="#l1147"></a>
<span id="l1148">                // if string is already quoted, don't re-quote</span><a href="#l1148"></a>
<span id="l1149">                if (!alreadyQuoted &amp;&amp; quoteNeeded) {</span><a href="#l1149"></a>
<span id="l1150">                    retval.append(&quot;\&quot;&quot; + sbuffer.toString() + &quot;\&quot;&quot;);</span><a href="#l1150"></a>
<span id="l1151">                } else {</span><a href="#l1151"></a>
<span id="l1152">                    retval.append(sbuffer.toString());</span><a href="#l1152"></a>
<span id="l1153">                }</span><a href="#l1153"></a>
<span id="l1154">            }</span><a href="#l1154"></a>
<span id="l1155">        } catch (IOException e) {</span><a href="#l1155"></a>
<span id="l1156">            throw new IllegalArgumentException(&quot;DER Value conversion&quot;);</span><a href="#l1156"></a>
<span id="l1157">        }</span><a href="#l1157"></a>
<span id="l1158"></span><a href="#l1158"></a>
<span id="l1159">        return retval.toString();</span><a href="#l1159"></a>
<span id="l1160">    }</span><a href="#l1160"></a>
<span id="l1161"></span><a href="#l1161"></a>
<span id="l1162">}</span><a href="#l1162"></a>
<span id="l1163"></span><a href="#l1163"></a>
<span id="l1164">/**</span><a href="#l1164"></a>
<span id="l1165"> * Helper class that allows conversion from String to ObjectIdentifier and</span><a href="#l1165"></a>
<span id="l1166"> * vice versa according to RFC1779, RFC2253, and an augmented version of</span><a href="#l1166"></a>
<span id="l1167"> * those standards.</span><a href="#l1167"></a>
<span id="l1168"> */</span><a href="#l1168"></a>
<span id="l1169">class AVAKeyword {</span><a href="#l1169"></a>
<span id="l1170"></span><a href="#l1170"></a>
<span id="l1171">    private static final Map&lt;ObjectIdentifier,AVAKeyword&gt; oidMap;</span><a href="#l1171"></a>
<span id="l1172">    private static final Map&lt;String,AVAKeyword&gt; keywordMap;</span><a href="#l1172"></a>
<span id="l1173"></span><a href="#l1173"></a>
<span id="l1174">    private String keyword;</span><a href="#l1174"></a>
<span id="l1175">    private ObjectIdentifier oid;</span><a href="#l1175"></a>
<span id="l1176">    private boolean rfc1779Compliant, rfc2253Compliant;</span><a href="#l1176"></a>
<span id="l1177"></span><a href="#l1177"></a>
<span id="l1178">    private AVAKeyword(String keyword, ObjectIdentifier oid,</span><a href="#l1178"></a>
<span id="l1179">               boolean rfc1779Compliant, boolean rfc2253Compliant) {</span><a href="#l1179"></a>
<span id="l1180">        this.keyword = keyword;</span><a href="#l1180"></a>
<span id="l1181">        this.oid = oid;</span><a href="#l1181"></a>
<span id="l1182">        this.rfc1779Compliant = rfc1779Compliant;</span><a href="#l1182"></a>
<span id="l1183">        this.rfc2253Compliant = rfc2253Compliant;</span><a href="#l1183"></a>
<span id="l1184"></span><a href="#l1184"></a>
<span id="l1185">        // register it</span><a href="#l1185"></a>
<span id="l1186">        oidMap.put(oid, this);</span><a href="#l1186"></a>
<span id="l1187">        keywordMap.put(keyword, this);</span><a href="#l1187"></a>
<span id="l1188">    }</span><a href="#l1188"></a>
<span id="l1189"></span><a href="#l1189"></a>
<span id="l1190">    private boolean isCompliant(int standard) {</span><a href="#l1190"></a>
<span id="l1191">        switch (standard) {</span><a href="#l1191"></a>
<span id="l1192">        case AVA.RFC1779:</span><a href="#l1192"></a>
<span id="l1193">            return rfc1779Compliant;</span><a href="#l1193"></a>
<span id="l1194">        case AVA.RFC2253:</span><a href="#l1194"></a>
<span id="l1195">            return rfc2253Compliant;</span><a href="#l1195"></a>
<span id="l1196">        case AVA.DEFAULT:</span><a href="#l1196"></a>
<span id="l1197">            return true;</span><a href="#l1197"></a>
<span id="l1198">        default:</span><a href="#l1198"></a>
<span id="l1199">            // should not occur, internal error</span><a href="#l1199"></a>
<span id="l1200">            throw new IllegalArgumentException(&quot;Invalid standard &quot; + standard);</span><a href="#l1200"></a>
<span id="l1201">        }</span><a href="#l1201"></a>
<span id="l1202">    }</span><a href="#l1202"></a>
<span id="l1203"></span><a href="#l1203"></a>
<span id="l1204">    /**</span><a href="#l1204"></a>
<span id="l1205">     * Get an object identifier representing the specified keyword (or</span><a href="#l1205"></a>
<span id="l1206">     * string encoded object identifier) in the given standard.</span><a href="#l1206"></a>
<span id="l1207">     *</span><a href="#l1207"></a>
<span id="l1208">     * @param keywordMap a Map where a keyword String maps to a corresponding</span><a href="#l1208"></a>
<span id="l1209">     *   OID String. Each AVA keyword will be mapped to the corresponding OID.</span><a href="#l1209"></a>
<span id="l1210">     *   If an entry does not exist, it will fallback to the builtin</span><a href="#l1210"></a>
<span id="l1211">     *   keyword/OID mapping.</span><a href="#l1211"></a>
<span id="l1212">     * @throws IOException If the keyword is not valid in the specified standard</span><a href="#l1212"></a>
<span id="l1213">     *   or the OID String to which a keyword maps to is improperly formatted.</span><a href="#l1213"></a>
<span id="l1214">     */</span><a href="#l1214"></a>
<span id="l1215">    static ObjectIdentifier getOID</span><a href="#l1215"></a>
<span id="l1216">        (String keyword, int standard, Map&lt;String, String&gt; extraKeywordMap)</span><a href="#l1216"></a>
<span id="l1217">            throws IOException {</span><a href="#l1217"></a>
<span id="l1218"></span><a href="#l1218"></a>
<span id="l1219">        keyword = keyword.toUpperCase(Locale.ENGLISH);</span><a href="#l1219"></a>
<span id="l1220">        if (standard == AVA.RFC2253) {</span><a href="#l1220"></a>
<span id="l1221">            if (keyword.startsWith(&quot; &quot;) || keyword.endsWith(&quot; &quot;)) {</span><a href="#l1221"></a>
<span id="l1222">                throw new IOException(&quot;Invalid leading or trailing space &quot; +</span><a href="#l1222"></a>
<span id="l1223">                        &quot;in keyword \&quot;&quot; + keyword + &quot;\&quot;&quot;);</span><a href="#l1223"></a>
<span id="l1224">            }</span><a href="#l1224"></a>
<span id="l1225">        } else {</span><a href="#l1225"></a>
<span id="l1226">            keyword = keyword.trim();</span><a href="#l1226"></a>
<span id="l1227">        }</span><a href="#l1227"></a>
<span id="l1228"></span><a href="#l1228"></a>
<span id="l1229">        // check user-specified keyword map first, then fallback to built-in</span><a href="#l1229"></a>
<span id="l1230">        // map</span><a href="#l1230"></a>
<span id="l1231">        String oidString = extraKeywordMap.get(keyword);</span><a href="#l1231"></a>
<span id="l1232">        if (oidString == null) {</span><a href="#l1232"></a>
<span id="l1233">            AVAKeyword ak = keywordMap.get(keyword);</span><a href="#l1233"></a>
<span id="l1234">            if ((ak != null) &amp;&amp; ak.isCompliant(standard)) {</span><a href="#l1234"></a>
<span id="l1235">                return ak.oid;</span><a href="#l1235"></a>
<span id="l1236">            }</span><a href="#l1236"></a>
<span id="l1237">        } else {</span><a href="#l1237"></a>
<span id="l1238">            return new ObjectIdentifier(oidString);</span><a href="#l1238"></a>
<span id="l1239">        }</span><a href="#l1239"></a>
<span id="l1240"></span><a href="#l1240"></a>
<span id="l1241">        // no keyword found, check if OID string</span><a href="#l1241"></a>
<span id="l1242">        if (standard == AVA.DEFAULT &amp;&amp; keyword.startsWith(&quot;OID.&quot;)) {</span><a href="#l1242"></a>
<span id="l1243">            keyword = keyword.substring(4);</span><a href="#l1243"></a>
<span id="l1244">        }</span><a href="#l1244"></a>
<span id="l1245"></span><a href="#l1245"></a>
<span id="l1246">        boolean number = false;</span><a href="#l1246"></a>
<span id="l1247">        if (keyword.length() != 0) {</span><a href="#l1247"></a>
<span id="l1248">            char ch = keyword.charAt(0);</span><a href="#l1248"></a>
<span id="l1249">            if ((ch &gt;= '0') &amp;&amp; (ch &lt;= '9')) {</span><a href="#l1249"></a>
<span id="l1250">                number = true;</span><a href="#l1250"></a>
<span id="l1251">            }</span><a href="#l1251"></a>
<span id="l1252">        }</span><a href="#l1252"></a>
<span id="l1253">        if (number == false) {</span><a href="#l1253"></a>
<span id="l1254">            throw new IOException(&quot;Invalid keyword \&quot;&quot; + keyword + &quot;\&quot;&quot;);</span><a href="#l1254"></a>
<span id="l1255">        }</span><a href="#l1255"></a>
<span id="l1256">        return new ObjectIdentifier(keyword);</span><a href="#l1256"></a>
<span id="l1257">    }</span><a href="#l1257"></a>
<span id="l1258"></span><a href="#l1258"></a>
<span id="l1259">    /**</span><a href="#l1259"></a>
<span id="l1260">     * Get a keyword for the given ObjectIdentifier according to standard.</span><a href="#l1260"></a>
<span id="l1261">     * If no keyword is available, the ObjectIdentifier is encoded as a</span><a href="#l1261"></a>
<span id="l1262">     * String.</span><a href="#l1262"></a>
<span id="l1263">     */</span><a href="#l1263"></a>
<span id="l1264">    static String getKeyword(ObjectIdentifier oid, int standard) {</span><a href="#l1264"></a>
<span id="l1265">        return getKeyword</span><a href="#l1265"></a>
<span id="l1266">            (oid, standard, Collections.&lt;String, String&gt;emptyMap());</span><a href="#l1266"></a>
<span id="l1267">    }</span><a href="#l1267"></a>
<span id="l1268"></span><a href="#l1268"></a>
<span id="l1269">    /**</span><a href="#l1269"></a>
<span id="l1270">     * Get a keyword for the given ObjectIdentifier according to standard.</span><a href="#l1270"></a>
<span id="l1271">     * Checks the extraOidMap for a keyword first, then falls back to the</span><a href="#l1271"></a>
<span id="l1272">     * builtin/default set. If no keyword is available, the ObjectIdentifier</span><a href="#l1272"></a>
<span id="l1273">     * is encoded as a String.</span><a href="#l1273"></a>
<span id="l1274">     */</span><a href="#l1274"></a>
<span id="l1275">    static String getKeyword</span><a href="#l1275"></a>
<span id="l1276">        (ObjectIdentifier oid, int standard, Map&lt;String, String&gt; extraOidMap) {</span><a href="#l1276"></a>
<span id="l1277"></span><a href="#l1277"></a>
<span id="l1278">        // check extraOidMap first, then fallback to built-in map</span><a href="#l1278"></a>
<span id="l1279">        String oidString = oid.toString();</span><a href="#l1279"></a>
<span id="l1280">        String keywordString = extraOidMap.get(oidString);</span><a href="#l1280"></a>
<span id="l1281">        if (keywordString == null) {</span><a href="#l1281"></a>
<span id="l1282">            AVAKeyword ak = oidMap.get(oid);</span><a href="#l1282"></a>
<span id="l1283">            if ((ak != null) &amp;&amp; ak.isCompliant(standard)) {</span><a href="#l1283"></a>
<span id="l1284">                return ak.keyword;</span><a href="#l1284"></a>
<span id="l1285">            }</span><a href="#l1285"></a>
<span id="l1286">        } else {</span><a href="#l1286"></a>
<span id="l1287">            if (keywordString.length() == 0) {</span><a href="#l1287"></a>
<span id="l1288">                throw new IllegalArgumentException(&quot;keyword cannot be empty&quot;);</span><a href="#l1288"></a>
<span id="l1289">            }</span><a href="#l1289"></a>
<span id="l1290">            keywordString = keywordString.trim();</span><a href="#l1290"></a>
<span id="l1291">            char c = keywordString.charAt(0);</span><a href="#l1291"></a>
<span id="l1292">            if (c &lt; 65 || c &gt; 122 || (c &gt; 90 &amp;&amp; c &lt; 97)) {</span><a href="#l1292"></a>
<span id="l1293">                throw new IllegalArgumentException</span><a href="#l1293"></a>
<span id="l1294">                    (&quot;keyword does not start with letter&quot;);</span><a href="#l1294"></a>
<span id="l1295">            }</span><a href="#l1295"></a>
<span id="l1296">            for (int i=1; i&lt;keywordString.length(); i++) {</span><a href="#l1296"></a>
<span id="l1297">                c = keywordString.charAt(i);</span><a href="#l1297"></a>
<span id="l1298">                if ((c &lt; 65 || c &gt; 122 || (c &gt; 90 &amp;&amp; c &lt; 97)) &amp;&amp;</span><a href="#l1298"></a>
<span id="l1299">                    (c &lt; 48 || c &gt; 57) &amp;&amp; c != '_') {</span><a href="#l1299"></a>
<span id="l1300">                    throw new IllegalArgumentException</span><a href="#l1300"></a>
<span id="l1301">                    (&quot;keyword character is not a letter, digit, or underscore&quot;);</span><a href="#l1301"></a>
<span id="l1302">                }</span><a href="#l1302"></a>
<span id="l1303">            }</span><a href="#l1303"></a>
<span id="l1304">            return keywordString;</span><a href="#l1304"></a>
<span id="l1305">        }</span><a href="#l1305"></a>
<span id="l1306">        // no compliant keyword, use OID</span><a href="#l1306"></a>
<span id="l1307">        if (standard == AVA.RFC2253) {</span><a href="#l1307"></a>
<span id="l1308">            return oidString;</span><a href="#l1308"></a>
<span id="l1309">        } else {</span><a href="#l1309"></a>
<span id="l1310">            return &quot;OID.&quot; + oidString;</span><a href="#l1310"></a>
<span id="l1311">        }</span><a href="#l1311"></a>
<span id="l1312">    }</span><a href="#l1312"></a>
<span id="l1313"></span><a href="#l1313"></a>
<span id="l1314">    /**</span><a href="#l1314"></a>
<span id="l1315">     * Test if oid has an associated keyword in standard.</span><a href="#l1315"></a>
<span id="l1316">     */</span><a href="#l1316"></a>
<span id="l1317">    static boolean hasKeyword(ObjectIdentifier oid, int standard) {</span><a href="#l1317"></a>
<span id="l1318">        AVAKeyword ak = oidMap.get(oid);</span><a href="#l1318"></a>
<span id="l1319">        if (ak == null) {</span><a href="#l1319"></a>
<span id="l1320">            return false;</span><a href="#l1320"></a>
<span id="l1321">        }</span><a href="#l1321"></a>
<span id="l1322">        return ak.isCompliant(standard);</span><a href="#l1322"></a>
<span id="l1323">    }</span><a href="#l1323"></a>
<span id="l1324"></span><a href="#l1324"></a>
<span id="l1325">    static {</span><a href="#l1325"></a>
<span id="l1326">        oidMap = new HashMap&lt;ObjectIdentifier,AVAKeyword&gt;();</span><a href="#l1326"></a>
<span id="l1327">        keywordMap = new HashMap&lt;String,AVAKeyword&gt;();</span><a href="#l1327"></a>
<span id="l1328"></span><a href="#l1328"></a>
<span id="l1329">        // NOTE if multiple keywords are available for one OID, order</span><a href="#l1329"></a>
<span id="l1330">        // is significant!! Preferred *LAST*.</span><a href="#l1330"></a>
<span id="l1331">        new AVAKeyword(&quot;CN&quot;,           X500Name.commonName_oid,   true,  true);</span><a href="#l1331"></a>
<span id="l1332">        new AVAKeyword(&quot;C&quot;,            X500Name.countryName_oid,  true,  true);</span><a href="#l1332"></a>
<span id="l1333">        new AVAKeyword(&quot;L&quot;,            X500Name.localityName_oid, true,  true);</span><a href="#l1333"></a>
<span id="l1334">        new AVAKeyword(&quot;S&quot;,            X500Name.stateName_oid,    false, false);</span><a href="#l1334"></a>
<span id="l1335">        new AVAKeyword(&quot;ST&quot;,           X500Name.stateName_oid,    true,  true);</span><a href="#l1335"></a>
<span id="l1336">        new AVAKeyword(&quot;O&quot;,            X500Name.orgName_oid,      true,  true);</span><a href="#l1336"></a>
<span id="l1337">        new AVAKeyword(&quot;OU&quot;,           X500Name.orgUnitName_oid,  true,  true);</span><a href="#l1337"></a>
<span id="l1338">        new AVAKeyword(&quot;T&quot;,            X500Name.title_oid,        false, false);</span><a href="#l1338"></a>
<span id="l1339">        new AVAKeyword(&quot;IP&quot;,           X500Name.ipAddress_oid,    false, false);</span><a href="#l1339"></a>
<span id="l1340">        new AVAKeyword(&quot;STREET&quot;,       X500Name.streetAddress_oid,true,  true);</span><a href="#l1340"></a>
<span id="l1341">        new AVAKeyword(&quot;DC&quot;,           X500Name.DOMAIN_COMPONENT_OID,</span><a href="#l1341"></a>
<span id="l1342">                                                                  false, true);</span><a href="#l1342"></a>
<span id="l1343">        new AVAKeyword(&quot;DNQUALIFIER&quot;,  X500Name.DNQUALIFIER_OID,  false, false);</span><a href="#l1343"></a>
<span id="l1344">        new AVAKeyword(&quot;DNQ&quot;,          X500Name.DNQUALIFIER_OID,  false, false);</span><a href="#l1344"></a>
<span id="l1345">        new AVAKeyword(&quot;SURNAME&quot;,      X500Name.SURNAME_OID,      false, false);</span><a href="#l1345"></a>
<span id="l1346">        new AVAKeyword(&quot;GIVENNAME&quot;,    X500Name.GIVENNAME_OID,    false, false);</span><a href="#l1346"></a>
<span id="l1347">        new AVAKeyword(&quot;INITIALS&quot;,     X500Name.INITIALS_OID,     false, false);</span><a href="#l1347"></a>
<span id="l1348">        new AVAKeyword(&quot;GENERATION&quot;,   X500Name.GENERATIONQUALIFIER_OID,</span><a href="#l1348"></a>
<span id="l1349">                                                                  false, false);</span><a href="#l1349"></a>
<span id="l1350">        new AVAKeyword(&quot;EMAIL&quot;, PKCS9Attribute.EMAIL_ADDRESS_OID, false, false);</span><a href="#l1350"></a>
<span id="l1351">        new AVAKeyword(&quot;EMAILADDRESS&quot;, PKCS9Attribute.EMAIL_ADDRESS_OID,</span><a href="#l1351"></a>
<span id="l1352">                                                                  false, false);</span><a href="#l1352"></a>
<span id="l1353">        new AVAKeyword(&quot;UID&quot;,          X500Name.userid_oid,       false, true);</span><a href="#l1353"></a>
<span id="l1354">        new AVAKeyword(&quot;SERIALNUMBER&quot;, X500Name.SERIALNUMBER_OID, false, false);</span><a href="#l1354"></a>
<span id="l1355">    }</span><a href="#l1355"></a>
<span id="l1356">}</span><a href="#l1356"></a></pre>
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

