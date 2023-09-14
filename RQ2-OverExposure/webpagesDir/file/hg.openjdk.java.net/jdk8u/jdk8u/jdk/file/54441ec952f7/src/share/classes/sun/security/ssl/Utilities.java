<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 54441ec952f7 src/share/classes/sun/security/ssl/Utilities.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/54441ec952f7">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/54441ec952f7">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/54441ec952f7">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/54441ec952f7/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/Utilities.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/54441ec952f7/src/share/classes/sun/security/ssl/Utilities.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/54441ec952f7/src/share/classes/sun/security/ssl/Utilities.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/54441ec952f7/src/share/classes/sun/security/ssl/Utilities.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/54441ec952f7/src/share/classes/sun/security/ssl/Utilities.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/54441ec952f7/src/share/classes/sun/security/ssl/Utilities.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/Utilities.java @ 14574:54441ec952f7</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8269618: Better session identification
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#99;&#118;&#101;&#114;&#103;&#104;&#101;&#115;&#101;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Fri, 15 Oct 2021 03:11:56 +0100</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/4546aa3faf37/src/share/classes/sun/security/ssl/Utilities.java">4546aa3faf37</a> </td>
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
<span id="l2"> * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.ssl;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.math.BigInteger;</span><a href="#l28"></a>
<span id="l29">import java.util.*;</span><a href="#l29"></a>
<span id="l30">import java.util.regex.Pattern;</span><a href="#l30"></a>
<span id="l31">import javax.net.ssl.*;</span><a href="#l31"></a>
<span id="l32">import sun.net.util.IPAddressUtil;</span><a href="#l32"></a>
<span id="l33">import sun.security.action.GetPropertyAction;</span><a href="#l33"></a>
<span id="l34"></span><a href="#l34"></a>
<span id="l35">/**</span><a href="#l35"></a>
<span id="l36"> * A utility class to share the static methods.</span><a href="#l36"></a>
<span id="l37"> */</span><a href="#l37"></a>
<span id="l38">final class Utilities {</span><a href="#l38"></a>
<span id="l39">    static final char[] hexDigits = &quot;0123456789ABCDEF&quot;.toCharArray();</span><a href="#l39"></a>
<span id="l40">    private static final String indent = &quot;  &quot;;</span><a href="#l40"></a>
<span id="l41">    private static final Pattern lineBreakPatern =</span><a href="#l41"></a>
<span id="l42">                Pattern.compile(&quot;\\r\\n|\\n|\\r&quot;);</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">    /**</span><a href="#l44"></a>
<span id="l45">     * Puts {@code hostname} into the {@code serverNames} list.</span><a href="#l45"></a>
<span id="l46">     * &lt;P&gt;</span><a href="#l46"></a>
<span id="l47">     * If the {@code serverNames} does not look like a legal FQDN, it will</span><a href="#l47"></a>
<span id="l48">     * not be put into the returned list.</span><a href="#l48"></a>
<span id="l49">     * &lt;P&gt;</span><a href="#l49"></a>
<span id="l50">     * Note that the returned list does not allow duplicated name type.</span><a href="#l50"></a>
<span id="l51">     *</span><a href="#l51"></a>
<span id="l52">     * @return a list of {@link SNIServerName}</span><a href="#l52"></a>
<span id="l53">     */</span><a href="#l53"></a>
<span id="l54">    static List&lt;SNIServerName&gt; addToSNIServerNameList(</span><a href="#l54"></a>
<span id="l55">            List&lt;SNIServerName&gt; serverNames, String hostname) {</span><a href="#l55"></a>
<span id="l56"></span><a href="#l56"></a>
<span id="l57">        SNIHostName sniHostName = rawToSNIHostName(hostname);</span><a href="#l57"></a>
<span id="l58">        if (sniHostName == null) {</span><a href="#l58"></a>
<span id="l59">            return serverNames;</span><a href="#l59"></a>
<span id="l60">        }</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">        int size = serverNames.size();</span><a href="#l62"></a>
<span id="l63">        List&lt;SNIServerName&gt; sniList = (size != 0) ?</span><a href="#l63"></a>
<span id="l64">                new ArrayList&lt;SNIServerName&gt;(serverNames) :</span><a href="#l64"></a>
<span id="l65">                new ArrayList&lt;SNIServerName&gt;(1);</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">        boolean reset = false;</span><a href="#l67"></a>
<span id="l68">        for (int i = 0; i &lt; size; i++) {</span><a href="#l68"></a>
<span id="l69">            SNIServerName serverName = sniList.get(i);</span><a href="#l69"></a>
<span id="l70">            if (serverName.getType() == StandardConstants.SNI_HOST_NAME) {</span><a href="#l70"></a>
<span id="l71">                sniList.set(i, sniHostName);</span><a href="#l71"></a>
<span id="l72">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl&quot;)) {</span><a href="#l72"></a>
<span id="l73">                     SSLLogger.fine(</span><a href="#l73"></a>
<span id="l74">                        &quot;the previous server name in SNI (&quot; + serverName +</span><a href="#l74"></a>
<span id="l75">                        &quot;) was replaced with (&quot; + sniHostName + &quot;)&quot;);</span><a href="#l75"></a>
<span id="l76">                }</span><a href="#l76"></a>
<span id="l77">                reset = true;</span><a href="#l77"></a>
<span id="l78">                break;</span><a href="#l78"></a>
<span id="l79">            }</span><a href="#l79"></a>
<span id="l80">        }</span><a href="#l80"></a>
<span id="l81"></span><a href="#l81"></a>
<span id="l82">        if (!reset) {</span><a href="#l82"></a>
<span id="l83">            sniList.add(sniHostName);</span><a href="#l83"></a>
<span id="l84">        }</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">        return Collections.&lt;SNIServerName&gt;unmodifiableList(sniList);</span><a href="#l86"></a>
<span id="l87">    }</span><a href="#l87"></a>
<span id="l88"></span><a href="#l88"></a>
<span id="l89">    /**</span><a href="#l89"></a>
<span id="l90">     * Converts string hostname to {@code SNIHostName}.</span><a href="#l90"></a>
<span id="l91">     * &lt;P&gt;</span><a href="#l91"></a>
<span id="l92">     * Note that to check whether a hostname is a valid domain name, we cannot</span><a href="#l92"></a>
<span id="l93">     * use the hostname resolved from name services.  For virtual hosting,</span><a href="#l93"></a>
<span id="l94">     * multiple hostnames may be bound to the same IP address, so the hostname</span><a href="#l94"></a>
<span id="l95">     * resolved from name services is not always reliable.</span><a href="#l95"></a>
<span id="l96">     *</span><a href="#l96"></a>
<span id="l97">     * @param  hostname</span><a href="#l97"></a>
<span id="l98">     *         the raw hostname</span><a href="#l98"></a>
<span id="l99">     * @return an instance of {@link SNIHostName}, or null if the hostname does</span><a href="#l99"></a>
<span id="l100">     *         not look like a FQDN</span><a href="#l100"></a>
<span id="l101">     */</span><a href="#l101"></a>
<span id="l102">    private static SNIHostName rawToSNIHostName(String hostname) {</span><a href="#l102"></a>
<span id="l103">        SNIHostName sniHostName = null;</span><a href="#l103"></a>
<span id="l104">        if (hostname != null &amp;&amp; hostname.indexOf('.') &gt; 0 &amp;&amp;</span><a href="#l104"></a>
<span id="l105">                !hostname.endsWith(&quot;.&quot;) &amp;&amp;</span><a href="#l105"></a>
<span id="l106">                !IPAddressUtil.isIPv4LiteralAddress(hostname) &amp;&amp;</span><a href="#l106"></a>
<span id="l107">                !IPAddressUtil.isIPv6LiteralAddress(hostname)) {</span><a href="#l107"></a>
<span id="l108"></span><a href="#l108"></a>
<span id="l109">            try {</span><a href="#l109"></a>
<span id="l110">                sniHostName = new SNIHostName(hostname);</span><a href="#l110"></a>
<span id="l111">            } catch (IllegalArgumentException iae) {</span><a href="#l111"></a>
<span id="l112">                // don't bother to handle illegal host_name</span><a href="#l112"></a>
<span id="l113">                if (SSLLogger.isOn &amp;&amp; SSLLogger.isOn(&quot;ssl&quot;)) {</span><a href="#l113"></a>
<span id="l114">                     SSLLogger.fine(hostname + &quot;\&quot; &quot; +</span><a href="#l114"></a>
<span id="l115">                        &quot;is not a legal HostName for  server name indication&quot;);</span><a href="#l115"></a>
<span id="l116">                }</span><a href="#l116"></a>
<span id="l117">            }</span><a href="#l117"></a>
<span id="l118">        }</span><a href="#l118"></a>
<span id="l119"></span><a href="#l119"></a>
<span id="l120">        return sniHostName;</span><a href="#l120"></a>
<span id="l121">    }</span><a href="#l121"></a>
<span id="l122"></span><a href="#l122"></a>
<span id="l123">    /**</span><a href="#l123"></a>
<span id="l124">     * Return the value of the boolean System property propName.</span><a href="#l124"></a>
<span id="l125">     *</span><a href="#l125"></a>
<span id="l126">     * Note use of privileged action. Do NOT make accessible to applications.</span><a href="#l126"></a>
<span id="l127">     */</span><a href="#l127"></a>
<span id="l128">    static boolean getBooleanProperty(String propName, boolean defaultValue) {</span><a href="#l128"></a>
<span id="l129">        // if set, require value of either true or false</span><a href="#l129"></a>
<span id="l130">        String b = GetPropertyAction.privilegedGetProperty(propName);</span><a href="#l130"></a>
<span id="l131">        if (b == null) {</span><a href="#l131"></a>
<span id="l132">            return defaultValue;</span><a href="#l132"></a>
<span id="l133">        } else if (b.equalsIgnoreCase(&quot;false&quot;)) {</span><a href="#l133"></a>
<span id="l134">            return false;</span><a href="#l134"></a>
<span id="l135">        } else if (b.equalsIgnoreCase(&quot;true&quot;)) {</span><a href="#l135"></a>
<span id="l136">            return true;</span><a href="#l136"></a>
<span id="l137">        } else {</span><a href="#l137"></a>
<span id="l138">            throw new RuntimeException(&quot;Value of &quot; + propName</span><a href="#l138"></a>
<span id="l139">                + &quot; must either be 'true' or 'false'&quot;);</span><a href="#l139"></a>
<span id="l140">        }</span><a href="#l140"></a>
<span id="l141">    }</span><a href="#l141"></a>
<span id="l142"></span><a href="#l142"></a>
<span id="l143">    static String indent(String source) {</span><a href="#l143"></a>
<span id="l144">        return Utilities.indent(source, indent);</span><a href="#l144"></a>
<span id="l145">    }</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">    static String indent(String source, String prefix) {</span><a href="#l147"></a>
<span id="l148">        StringBuilder builder = new StringBuilder();</span><a href="#l148"></a>
<span id="l149">        if (source == null) {</span><a href="#l149"></a>
<span id="l150">             builder.append(&quot;\n&quot; + prefix + &quot;&lt;blank message&gt;&quot;);</span><a href="#l150"></a>
<span id="l151">        } else {</span><a href="#l151"></a>
<span id="l152">            String[] lines = lineBreakPatern.split(source);</span><a href="#l152"></a>
<span id="l153">            boolean isFirst = true;</span><a href="#l153"></a>
<span id="l154">            for (String line : lines) {</span><a href="#l154"></a>
<span id="l155">                if (isFirst) {</span><a href="#l155"></a>
<span id="l156">                    isFirst = false;</span><a href="#l156"></a>
<span id="l157">                } else {</span><a href="#l157"></a>
<span id="l158">                    builder.append(&quot;\n&quot;);</span><a href="#l158"></a>
<span id="l159">                }</span><a href="#l159"></a>
<span id="l160">                builder.append(prefix).append(line);</span><a href="#l160"></a>
<span id="l161">            }</span><a href="#l161"></a>
<span id="l162">        }</span><a href="#l162"></a>
<span id="l163"></span><a href="#l163"></a>
<span id="l164">        return builder.toString();</span><a href="#l164"></a>
<span id="l165">    }</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    static String toHexString(byte b) {</span><a href="#l167"></a>
<span id="l168">        return String.valueOf(hexDigits[(b &gt;&gt; 4) &amp; 0x0F]) +</span><a href="#l168"></a>
<span id="l169">                String.valueOf(hexDigits[b &amp; 0x0F]);</span><a href="#l169"></a>
<span id="l170">    }</span><a href="#l170"></a>
<span id="l171"></span><a href="#l171"></a>
<span id="l172">    static String byte16HexString(int id) {</span><a href="#l172"></a>
<span id="l173">        return &quot;0x&quot; +</span><a href="#l173"></a>
<span id="l174">                hexDigits[(id &gt;&gt; 12) &amp; 0x0F] + hexDigits[(id &gt;&gt; 8) &amp; 0x0F] +</span><a href="#l174"></a>
<span id="l175">                hexDigits[(id &gt;&gt; 4) &amp; 0x0F] + hexDigits[id &amp; 0x0F];</span><a href="#l175"></a>
<span id="l176">    }</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">    static String toHexString(byte[] bytes) {</span><a href="#l178"></a>
<span id="l179">        if (bytes == null || bytes.length == 0) {</span><a href="#l179"></a>
<span id="l180">            return &quot;&quot;;</span><a href="#l180"></a>
<span id="l181">        }</span><a href="#l181"></a>
<span id="l182"></span><a href="#l182"></a>
<span id="l183">        StringBuilder builder = new StringBuilder(bytes.length * 3);</span><a href="#l183"></a>
<span id="l184">        boolean isFirst = true;</span><a href="#l184"></a>
<span id="l185">        for (byte b : bytes) {</span><a href="#l185"></a>
<span id="l186">            if (isFirst) {</span><a href="#l186"></a>
<span id="l187">                isFirst = false;</span><a href="#l187"></a>
<span id="l188">            } else {</span><a href="#l188"></a>
<span id="l189">                builder.append(' ');</span><a href="#l189"></a>
<span id="l190">            }</span><a href="#l190"></a>
<span id="l191"></span><a href="#l191"></a>
<span id="l192">            builder.append(hexDigits[(b &gt;&gt; 4) &amp; 0x0F]);</span><a href="#l192"></a>
<span id="l193">            builder.append(hexDigits[b &amp; 0x0F]);</span><a href="#l193"></a>
<span id="l194">        }</span><a href="#l194"></a>
<span id="l195">        return builder.toString();</span><a href="#l195"></a>
<span id="l196">    }</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">    static String toHexString(long lv) {</span><a href="#l198"></a>
<span id="l199">        StringBuilder builder = new StringBuilder(128);</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">        boolean isFirst = true;</span><a href="#l201"></a>
<span id="l202">        do {</span><a href="#l202"></a>
<span id="l203">            if (isFirst) {</span><a href="#l203"></a>
<span id="l204">                isFirst = false;</span><a href="#l204"></a>
<span id="l205">            } else {</span><a href="#l205"></a>
<span id="l206">                builder.append(' ');</span><a href="#l206"></a>
<span id="l207">            }</span><a href="#l207"></a>
<span id="l208"></span><a href="#l208"></a>
<span id="l209">            builder.append(hexDigits[(int)(lv &amp; 0x0F)]);</span><a href="#l209"></a>
<span id="l210">            lv &gt;&gt;&gt;= 4;</span><a href="#l210"></a>
<span id="l211">            builder.append(hexDigits[(int)(lv &amp; 0x0F)]);</span><a href="#l211"></a>
<span id="l212">            lv &gt;&gt;&gt;= 4;</span><a href="#l212"></a>
<span id="l213">        } while (lv != 0);</span><a href="#l213"></a>
<span id="l214">        builder.reverse();</span><a href="#l214"></a>
<span id="l215"></span><a href="#l215"></a>
<span id="l216">        return builder.toString();</span><a href="#l216"></a>
<span id="l217">    }</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">    /**</span><a href="#l219"></a>
<span id="l220">     * Utility method to convert a BigInteger to a byte array in unsigned</span><a href="#l220"></a>
<span id="l221">     * format as needed in the handshake messages. BigInteger uses</span><a href="#l221"></a>
<span id="l222">     * 2's complement format, i.e. it prepends an extra zero if the MSB</span><a href="#l222"></a>
<span id="l223">     * is set. We remove that.</span><a href="#l223"></a>
<span id="l224">     */</span><a href="#l224"></a>
<span id="l225">    static byte[] toByteArray(BigInteger bi) {</span><a href="#l225"></a>
<span id="l226">        byte[] b = bi.toByteArray();</span><a href="#l226"></a>
<span id="l227">        if ((b.length &gt; 1) &amp;&amp; (b[0] == 0)) {</span><a href="#l227"></a>
<span id="l228">            int n = b.length - 1;</span><a href="#l228"></a>
<span id="l229">            byte[] newarray = new byte[n];</span><a href="#l229"></a>
<span id="l230">            System.arraycopy(b, 1, newarray, 0, n);</span><a href="#l230"></a>
<span id="l231">            b = newarray;</span><a href="#l231"></a>
<span id="l232">        }</span><a href="#l232"></a>
<span id="l233">        return b;</span><a href="#l233"></a>
<span id="l234">    }</span><a href="#l234"></a>
<span id="l235"></span><a href="#l235"></a>
<span id="l236">    /**</span><a href="#l236"></a>
<span id="l237">     * Checks that {@code fromIndex} and {@code toIndex} are in</span><a href="#l237"></a>
<span id="l238">     * the range and throws an exception if they aren't.</span><a href="#l238"></a>
<span id="l239">     */</span><a href="#l239"></a>
<span id="l240">    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {</span><a href="#l240"></a>
<span id="l241">        if (fromIndex &gt; toIndex) {</span><a href="#l241"></a>
<span id="l242">            throw new IllegalArgumentException(</span><a href="#l242"></a>
<span id="l243">                    &quot;fromIndex(&quot; + fromIndex + &quot;) &gt; toIndex(&quot; + toIndex + &quot;)&quot;);</span><a href="#l243"></a>
<span id="l244">        }</span><a href="#l244"></a>
<span id="l245">        if (fromIndex &lt; 0) {</span><a href="#l245"></a>
<span id="l246">            throw new ArrayIndexOutOfBoundsException(fromIndex);</span><a href="#l246"></a>
<span id="l247">        }</span><a href="#l247"></a>
<span id="l248">        if (toIndex &gt; arrayLength) {</span><a href="#l248"></a>
<span id="l249">            throw new ArrayIndexOutOfBoundsException(toIndex);</span><a href="#l249"></a>
<span id="l250">        }</span><a href="#l250"></a>
<span id="l251">    }</span><a href="#l251"></a>
<span id="l252"></span><a href="#l252"></a>
<span id="l253">}</span><a href="#l253"></a></pre>
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

