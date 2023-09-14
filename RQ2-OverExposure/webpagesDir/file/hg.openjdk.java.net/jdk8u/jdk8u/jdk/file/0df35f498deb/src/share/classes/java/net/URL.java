<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 0df35f498deb src/share/classes/java/net/URL.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/0df35f498deb">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/0df35f498deb">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/0df35f498deb">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/0df35f498deb/src/share/classes/java/net/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/java/net/URL.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/0df35f498deb/src/share/classes/java/net/URL.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/0df35f498deb/src/share/classes/java/net/URL.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/0df35f498deb/src/share/classes/java/net/URL.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/0df35f498deb/src/share/classes/java/net/URL.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/0df35f498deb/src/share/classes/java/net/URL.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/java/net/URL.java @ 13765:0df35f498deb</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8228548: Normalize normalization for all
Reviewed-by: chegar, rhalade, igerasim, bae</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#105;&#103;&#101;&#114;&#97;&#115;&#105;&#109;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Wed, 14 Aug 2019 17:24:43 -0700</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/1f801b99581a/src/share/classes/java/net/URL.java">1f801b99581a</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/eac7639b533d/src/share/classes/java/net/URL.java">eac7639b533d</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 1995, 2019, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package java.net;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.io.InputStream;</span><a href="#l29"></a>
<span id="l30">import java.io.InvalidObjectException;</span><a href="#l30"></a>
<span id="l31">import java.io.ObjectStreamException;</span><a href="#l31"></a>
<span id="l32">import java.io.ObjectStreamField;</span><a href="#l32"></a>
<span id="l33">import java.io.ObjectInputStream.GetField;</span><a href="#l33"></a>
<span id="l34">import java.util.Hashtable;</span><a href="#l34"></a>
<span id="l35">import java.util.StringTokenizer;</span><a href="#l35"></a>
<span id="l36">import sun.misc.VM;</span><a href="#l36"></a>
<span id="l37">import sun.net.util.IPAddressUtil;</span><a href="#l37"></a>
<span id="l38">import sun.security.util.SecurityConstants;</span><a href="#l38"></a>
<span id="l39"></span><a href="#l39"></a>
<span id="l40">/**</span><a href="#l40"></a>
<span id="l41"> * Class {@code URL} represents a Uniform Resource</span><a href="#l41"></a>
<span id="l42"> * Locator, a pointer to a &quot;resource&quot; on the World</span><a href="#l42"></a>
<span id="l43"> * Wide Web. A resource can be something as simple as a file or a</span><a href="#l43"></a>
<span id="l44"> * directory, or it can be a reference to a more complicated object,</span><a href="#l44"></a>
<span id="l45"> * such as a query to a database or to a search engine. More</span><a href="#l45"></a>
<span id="l46"> * information on the types of URLs and their formats can be found at:</span><a href="#l46"></a>
<span id="l47"> * &lt;a href=</span><a href="#l47"></a>
<span id="l48"> * &quot;http://web.archive.org/web/20051219043731/http://archive.ncsa.uiuc.edu/SDG/Software/Mosaic/Demo/url-primer.html&quot;&gt;</span><a href="#l48"></a>
<span id="l49"> * &lt;i&gt;Types of URL&lt;/i&gt;&lt;/a&gt;</span><a href="#l49"></a>
<span id="l50"> * &lt;p&gt;</span><a href="#l50"></a>
<span id="l51"> * In general, a URL can be broken into several parts. Consider the</span><a href="#l51"></a>
<span id="l52"> * following example:</span><a href="#l52"></a>
<span id="l53"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l53"></a>
<span id="l54"> *     http://www.example.com/docs/resource1.html</span><a href="#l54"></a>
<span id="l55"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l55"></a>
<span id="l56"> * &lt;p&gt;</span><a href="#l56"></a>
<span id="l57"> * The URL above indicates that the protocol to use is</span><a href="#l57"></a>
<span id="l58"> * {@code http} (HyperText Transfer Protocol) and that the</span><a href="#l58"></a>
<span id="l59"> * information resides on a host machine named</span><a href="#l59"></a>
<span id="l60"> * {@code www.example.com}. The information on that host</span><a href="#l60"></a>
<span id="l61"> * machine is named {@code /docs/resource1.html}. The exact</span><a href="#l61"></a>
<span id="l62"> * meaning of this name on the host machine is both protocol</span><a href="#l62"></a>
<span id="l63"> * dependent and host dependent. The information normally resides in</span><a href="#l63"></a>
<span id="l64"> * a file, but it could be generated on the fly. This component of</span><a href="#l64"></a>
<span id="l65"> * the URL is called the &lt;i&gt;path&lt;/i&gt; component.</span><a href="#l65"></a>
<span id="l66"> * &lt;p&gt;</span><a href="#l66"></a>
<span id="l67"> * A URL can optionally specify a &quot;port&quot;, which is the</span><a href="#l67"></a>
<span id="l68"> * port number to which the TCP connection is made on the remote host</span><a href="#l68"></a>
<span id="l69"> * machine. If the port is not specified, the default port for</span><a href="#l69"></a>
<span id="l70"> * the protocol is used instead. For example, the default port for</span><a href="#l70"></a>
<span id="l71"> * {@code http} is {@code 80}. An alternative port could be</span><a href="#l71"></a>
<span id="l72"> * specified as:</span><a href="#l72"></a>
<span id="l73"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l73"></a>
<span id="l74"> *     http://www.example.com:1080/docs/resource1.html</span><a href="#l74"></a>
<span id="l75"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l75"></a>
<span id="l76"> * &lt;p&gt;</span><a href="#l76"></a>
<span id="l77"> * The syntax of {@code URL} is defined by  &lt;a</span><a href="#l77"></a>
<span id="l78"> * href=&quot;http://www.ietf.org/rfc/rfc2396.txt&quot;&gt;&lt;i&gt;RFC&amp;nbsp;2396: Uniform</span><a href="#l78"></a>
<span id="l79"> * Resource Identifiers (URI): Generic Syntax&lt;/i&gt;&lt;/a&gt;, amended by &lt;a</span><a href="#l79"></a>
<span id="l80"> * href=&quot;http://www.ietf.org/rfc/rfc2732.txt&quot;&gt;&lt;i&gt;RFC&amp;nbsp;2732: Format for</span><a href="#l80"></a>
<span id="l81"> * Literal IPv6 Addresses in URLs&lt;/i&gt;&lt;/a&gt;. The Literal IPv6 address format</span><a href="#l81"></a>
<span id="l82"> * also supports scope_ids. The syntax and usage of scope_ids is described</span><a href="#l82"></a>
<span id="l83"> * &lt;a href=&quot;Inet6Address.html#scoped&quot;&gt;here&lt;/a&gt;.</span><a href="#l83"></a>
<span id="l84"> * &lt;p&gt;</span><a href="#l84"></a>
<span id="l85"> * A URL may have appended to it a &quot;fragment&quot;, also known</span><a href="#l85"></a>
<span id="l86"> * as a &quot;ref&quot; or a &quot;reference&quot;. The fragment is indicated by the sharp</span><a href="#l86"></a>
<span id="l87"> * sign character &quot;#&quot; followed by more characters. For example,</span><a href="#l87"></a>
<span id="l88"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l88"></a>
<span id="l89"> *     http://java.sun.com/index.html#chapter1</span><a href="#l89"></a>
<span id="l90"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l90"></a>
<span id="l91"> * &lt;p&gt;</span><a href="#l91"></a>
<span id="l92"> * This fragment is not technically part of the URL. Rather, it</span><a href="#l92"></a>
<span id="l93"> * indicates that after the specified resource is retrieved, the</span><a href="#l93"></a>
<span id="l94"> * application is specifically interested in that part of the</span><a href="#l94"></a>
<span id="l95"> * document that has the tag {@code chapter1} attached to it. The</span><a href="#l95"></a>
<span id="l96"> * meaning of a tag is resource specific.</span><a href="#l96"></a>
<span id="l97"> * &lt;p&gt;</span><a href="#l97"></a>
<span id="l98"> * An application can also specify a &quot;relative URL&quot;,</span><a href="#l98"></a>
<span id="l99"> * which contains only enough information to reach the resource</span><a href="#l99"></a>
<span id="l100"> * relative to another URL. Relative URLs are frequently used within</span><a href="#l100"></a>
<span id="l101"> * HTML pages. For example, if the contents of the URL:</span><a href="#l101"></a>
<span id="l102"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l102"></a>
<span id="l103"> *     http://java.sun.com/index.html</span><a href="#l103"></a>
<span id="l104"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l104"></a>
<span id="l105"> * contained within it the relative URL:</span><a href="#l105"></a>
<span id="l106"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l106"></a>
<span id="l107"> *     FAQ.html</span><a href="#l107"></a>
<span id="l108"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l108"></a>
<span id="l109"> * it would be a shorthand for:</span><a href="#l109"></a>
<span id="l110"> * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l110"></a>
<span id="l111"> *     http://java.sun.com/FAQ.html</span><a href="#l111"></a>
<span id="l112"> * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l112"></a>
<span id="l113"> * &lt;p&gt;</span><a href="#l113"></a>
<span id="l114"> * The relative URL need not specify all the components of a URL. If</span><a href="#l114"></a>
<span id="l115"> * the protocol, host name, or port number is missing, the value is</span><a href="#l115"></a>
<span id="l116"> * inherited from the fully specified URL. The file component must be</span><a href="#l116"></a>
<span id="l117"> * specified. The optional fragment is not inherited.</span><a href="#l117"></a>
<span id="l118"> * &lt;p&gt;</span><a href="#l118"></a>
<span id="l119"> * The URL class does not itself encode or decode any URL components</span><a href="#l119"></a>
<span id="l120"> * according to the escaping mechanism defined in RFC2396. It is the</span><a href="#l120"></a>
<span id="l121"> * responsibility of the caller to encode any fields, which need to be</span><a href="#l121"></a>
<span id="l122"> * escaped prior to calling URL, and also to decode any escaped fields,</span><a href="#l122"></a>
<span id="l123"> * that are returned from URL. Furthermore, because URL has no knowledge</span><a href="#l123"></a>
<span id="l124"> * of URL escaping, it does not recognise equivalence between the encoded</span><a href="#l124"></a>
<span id="l125"> * or decoded form of the same URL. For example, the two URLs:&lt;br&gt;</span><a href="#l125"></a>
<span id="l126"> * &lt;pre&gt;    http://foo.com/hello world/ and http://foo.com/hello%20world&lt;/pre&gt;</span><a href="#l126"></a>
<span id="l127"> * would be considered not equal to each other.</span><a href="#l127"></a>
<span id="l128"> * &lt;p&gt;</span><a href="#l128"></a>
<span id="l129"> * Note, the {@link java.net.URI} class does perform escaping of its</span><a href="#l129"></a>
<span id="l130"> * component fields in certain circumstances. The recommended way</span><a href="#l130"></a>
<span id="l131"> * to manage the encoding and decoding of URLs is to use {@link java.net.URI},</span><a href="#l131"></a>
<span id="l132"> * and to convert between these two classes using {@link #toURI()} and</span><a href="#l132"></a>
<span id="l133"> * {@link URI#toURL()}.</span><a href="#l133"></a>
<span id="l134"> * &lt;p&gt;</span><a href="#l134"></a>
<span id="l135"> * The {@link URLEncoder} and {@link URLDecoder} classes can also be</span><a href="#l135"></a>
<span id="l136"> * used, but only for HTML form encoding, which is not the same</span><a href="#l136"></a>
<span id="l137"> * as the encoding scheme defined in RFC2396.</span><a href="#l137"></a>
<span id="l138"> *</span><a href="#l138"></a>
<span id="l139"> * @author  James Gosling</span><a href="#l139"></a>
<span id="l140"> * @since JDK1.0</span><a href="#l140"></a>
<span id="l141"> */</span><a href="#l141"></a>
<span id="l142">public final class URL implements java.io.Serializable {</span><a href="#l142"></a>
<span id="l143"></span><a href="#l143"></a>
<span id="l144">    static final String BUILTIN_HANDLERS_PREFIX = &quot;sun.net.www.protocol&quot;;</span><a href="#l144"></a>
<span id="l145">    static final long serialVersionUID = -7627629688361524110L;</span><a href="#l145"></a>
<span id="l146"></span><a href="#l146"></a>
<span id="l147">    /**</span><a href="#l147"></a>
<span id="l148">     * The property which specifies the package prefix list to be scanned</span><a href="#l148"></a>
<span id="l149">     * for protocol handlers.  The value of this property (if any) should</span><a href="#l149"></a>
<span id="l150">     * be a vertical bar delimited list of package names to search through</span><a href="#l150"></a>
<span id="l151">     * for a protocol handler to load.  The policy of this class is that</span><a href="#l151"></a>
<span id="l152">     * all protocol handlers will be in a class called &lt;protocolname&gt;.Handler,</span><a href="#l152"></a>
<span id="l153">     * and each package in the list is examined in turn for a matching</span><a href="#l153"></a>
<span id="l154">     * handler.  If none are found (or the property is not specified), the</span><a href="#l154"></a>
<span id="l155">     * default package prefix, sun.net.www.protocol, is used.  The search</span><a href="#l155"></a>
<span id="l156">     * proceeds from the first package in the list to the last and stops</span><a href="#l156"></a>
<span id="l157">     * when a match is found.</span><a href="#l157"></a>
<span id="l158">     */</span><a href="#l158"></a>
<span id="l159">    private static final String protocolPathProp = &quot;java.protocol.handler.pkgs&quot;;</span><a href="#l159"></a>
<span id="l160"></span><a href="#l160"></a>
<span id="l161">    /**</span><a href="#l161"></a>
<span id="l162">     * The protocol to use (ftp, http, nntp, ... etc.) .</span><a href="#l162"></a>
<span id="l163">     * @serial</span><a href="#l163"></a>
<span id="l164">     */</span><a href="#l164"></a>
<span id="l165">    private String protocol;</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    /**</span><a href="#l167"></a>
<span id="l168">     * The host name to connect to.</span><a href="#l168"></a>
<span id="l169">     * @serial</span><a href="#l169"></a>
<span id="l170">     */</span><a href="#l170"></a>
<span id="l171">    private String host;</span><a href="#l171"></a>
<span id="l172"></span><a href="#l172"></a>
<span id="l173">    /**</span><a href="#l173"></a>
<span id="l174">     * The protocol port to connect to.</span><a href="#l174"></a>
<span id="l175">     * @serial</span><a href="#l175"></a>
<span id="l176">     */</span><a href="#l176"></a>
<span id="l177">    private int port = -1;</span><a href="#l177"></a>
<span id="l178"></span><a href="#l178"></a>
<span id="l179">    /**</span><a href="#l179"></a>
<span id="l180">     * The specified file name on that host. {@code file} is</span><a href="#l180"></a>
<span id="l181">     * defined as {@code path[?query]}</span><a href="#l181"></a>
<span id="l182">     * @serial</span><a href="#l182"></a>
<span id="l183">     */</span><a href="#l183"></a>
<span id="l184">    private String file;</span><a href="#l184"></a>
<span id="l185"></span><a href="#l185"></a>
<span id="l186">    /**</span><a href="#l186"></a>
<span id="l187">     * The query part of this URL.</span><a href="#l187"></a>
<span id="l188">     */</span><a href="#l188"></a>
<span id="l189">    private transient String query;</span><a href="#l189"></a>
<span id="l190"></span><a href="#l190"></a>
<span id="l191">    /**</span><a href="#l191"></a>
<span id="l192">     * The authority part of this URL.</span><a href="#l192"></a>
<span id="l193">     * @serial</span><a href="#l193"></a>
<span id="l194">     */</span><a href="#l194"></a>
<span id="l195">    private String authority;</span><a href="#l195"></a>
<span id="l196"></span><a href="#l196"></a>
<span id="l197">    /**</span><a href="#l197"></a>
<span id="l198">     * The path part of this URL.</span><a href="#l198"></a>
<span id="l199">     */</span><a href="#l199"></a>
<span id="l200">    private transient String path;</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">    /**</span><a href="#l202"></a>
<span id="l203">     * The userinfo part of this URL.</span><a href="#l203"></a>
<span id="l204">     */</span><a href="#l204"></a>
<span id="l205">    private transient String userInfo;</span><a href="#l205"></a>
<span id="l206"></span><a href="#l206"></a>
<span id="l207">    /**</span><a href="#l207"></a>
<span id="l208">     * # reference.</span><a href="#l208"></a>
<span id="l209">     * @serial</span><a href="#l209"></a>
<span id="l210">     */</span><a href="#l210"></a>
<span id="l211">    private String ref;</span><a href="#l211"></a>
<span id="l212"></span><a href="#l212"></a>
<span id="l213">    /**</span><a href="#l213"></a>
<span id="l214">     * The host's IP address, used in equals and hashCode.</span><a href="#l214"></a>
<span id="l215">     * Computed on demand. An uninitialized or unknown hostAddress is null.</span><a href="#l215"></a>
<span id="l216">     */</span><a href="#l216"></a>
<span id="l217">    transient InetAddress hostAddress;</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">    /**</span><a href="#l219"></a>
<span id="l220">     * The URLStreamHandler for this URL.</span><a href="#l220"></a>
<span id="l221">     */</span><a href="#l221"></a>
<span id="l222">    transient URLStreamHandler handler;</span><a href="#l222"></a>
<span id="l223"></span><a href="#l223"></a>
<span id="l224">    /* Our hash code.</span><a href="#l224"></a>
<span id="l225">     * @serial</span><a href="#l225"></a>
<span id="l226">     */</span><a href="#l226"></a>
<span id="l227">    private int hashCode = -1;</span><a href="#l227"></a>
<span id="l228"></span><a href="#l228"></a>
<span id="l229">    private transient UrlDeserializedState tempState;</span><a href="#l229"></a>
<span id="l230"></span><a href="#l230"></a>
<span id="l231">    /**</span><a href="#l231"></a>
<span id="l232">     * Creates a {@code URL} object from the specified</span><a href="#l232"></a>
<span id="l233">     * {@code protocol}, {@code host}, {@code port}</span><a href="#l233"></a>
<span id="l234">     * number, and {@code file}.&lt;p&gt;</span><a href="#l234"></a>
<span id="l235">     *</span><a href="#l235"></a>
<span id="l236">     * {@code host} can be expressed as a host name or a literal</span><a href="#l236"></a>
<span id="l237">     * IP address. If IPv6 literal address is used, it should be</span><a href="#l237"></a>
<span id="l238">     * enclosed in square brackets ({@code '['} and {@code ']'}), as</span><a href="#l238"></a>
<span id="l239">     * specified by &lt;a</span><a href="#l239"></a>
<span id="l240">     * href=&quot;http://www.ietf.org/rfc/rfc2732.txt&quot;&gt;RFC&amp;nbsp;2732&lt;/a&gt;;</span><a href="#l240"></a>
<span id="l241">     * However, the literal IPv6 address format defined in &lt;a</span><a href="#l241"></a>
<span id="l242">     * href=&quot;http://www.ietf.org/rfc/rfc2373.txt&quot;&gt;&lt;i&gt;RFC&amp;nbsp;2373: IP</span><a href="#l242"></a>
<span id="l243">     * Version 6 Addressing Architecture&lt;/i&gt;&lt;/a&gt; is also accepted.&lt;p&gt;</span><a href="#l243"></a>
<span id="l244">     *</span><a href="#l244"></a>
<span id="l245">     * Specifying a {@code port} number of {@code -1}</span><a href="#l245"></a>
<span id="l246">     * indicates that the URL should use the default port for the</span><a href="#l246"></a>
<span id="l247">     * protocol.&lt;p&gt;</span><a href="#l247"></a>
<span id="l248">     *</span><a href="#l248"></a>
<span id="l249">     * If this is the first URL object being created with the specified</span><a href="#l249"></a>
<span id="l250">     * protocol, a &lt;i&gt;stream protocol handler&lt;/i&gt; object, an instance of</span><a href="#l250"></a>
<span id="l251">     * class {@code URLStreamHandler}, is created for that protocol:</span><a href="#l251"></a>
<span id="l252">     * &lt;ol&gt;</span><a href="#l252"></a>
<span id="l253">     * &lt;li&gt;If the application has previously set up an instance of</span><a href="#l253"></a>
<span id="l254">     *     {@code URLStreamHandlerFactory} as the stream handler factory,</span><a href="#l254"></a>
<span id="l255">     *     then the {@code createURLStreamHandler} method of that instance</span><a href="#l255"></a>
<span id="l256">     *     is called with the protocol string as an argument to create the</span><a href="#l256"></a>
<span id="l257">     *     stream protocol handler.</span><a href="#l257"></a>
<span id="l258">     * &lt;li&gt;If no {@code URLStreamHandlerFactory} has yet been set up,</span><a href="#l258"></a>
<span id="l259">     *     or if the factory's {@code createURLStreamHandler} method</span><a href="#l259"></a>
<span id="l260">     *     returns {@code null}, then the constructor finds the</span><a href="#l260"></a>
<span id="l261">     *     value of the system property:</span><a href="#l261"></a>
<span id="l262">     *     &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l262"></a>
<span id="l263">     *         java.protocol.handler.pkgs</span><a href="#l263"></a>
<span id="l264">     *     &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l264"></a>
<span id="l265">     *     If the value of that system property is not {@code null},</span><a href="#l265"></a>
<span id="l266">     *     it is interpreted as a list of packages separated by a vertical</span><a href="#l266"></a>
<span id="l267">     *     slash character '{@code |}'. The constructor tries to load</span><a href="#l267"></a>
<span id="l268">     *     the class named:</span><a href="#l268"></a>
<span id="l269">     *     &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l269"></a>
<span id="l270">     *         &amp;lt;&lt;i&gt;package&lt;/i&gt;&amp;gt;.&amp;lt;&lt;i&gt;protocol&lt;/i&gt;&amp;gt;.Handler</span><a href="#l270"></a>
<span id="l271">     *     &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l271"></a>
<span id="l272">     *     where &amp;lt;&lt;i&gt;package&lt;/i&gt;&amp;gt; is replaced by the name of the package</span><a href="#l272"></a>
<span id="l273">     *     and &amp;lt;&lt;i&gt;protocol&lt;/i&gt;&amp;gt; is replaced by the name of the protocol.</span><a href="#l273"></a>
<span id="l274">     *     If this class does not exist, or if the class exists but it is not</span><a href="#l274"></a>
<span id="l275">     *     a subclass of {@code URLStreamHandler}, then the next package</span><a href="#l275"></a>
<span id="l276">     *     in the list is tried.</span><a href="#l276"></a>
<span id="l277">     * &lt;li&gt;If the previous step fails to find a protocol handler, then the</span><a href="#l277"></a>
<span id="l278">     *     constructor tries to load from a system default package.</span><a href="#l278"></a>
<span id="l279">     *     &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l279"></a>
<span id="l280">     *         &amp;lt;&lt;i&gt;system default package&lt;/i&gt;&amp;gt;.&amp;lt;&lt;i&gt;protocol&lt;/i&gt;&amp;gt;.Handler</span><a href="#l280"></a>
<span id="l281">     *     &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l281"></a>
<span id="l282">     *     If this class does not exist, or if the class exists but it is not a</span><a href="#l282"></a>
<span id="l283">     *     subclass of {@code URLStreamHandler}, then a</span><a href="#l283"></a>
<span id="l284">     *     {@code MalformedURLException} is thrown.</span><a href="#l284"></a>
<span id="l285">     * &lt;/ol&gt;</span><a href="#l285"></a>
<span id="l286">     *</span><a href="#l286"></a>
<span id="l287">     * &lt;p&gt;Protocol handlers for the following protocols are guaranteed</span><a href="#l287"></a>
<span id="l288">     * to exist on the search path :-</span><a href="#l288"></a>
<span id="l289">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l289"></a>
<span id="l290">     *     http, https, file, and jar</span><a href="#l290"></a>
<span id="l291">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l291"></a>
<span id="l292">     * Protocol handlers for additional protocols may also be</span><a href="#l292"></a>
<span id="l293">     * available.</span><a href="#l293"></a>
<span id="l294">     *</span><a href="#l294"></a>
<span id="l295">     * &lt;p&gt;No validation of the inputs is performed by this constructor.</span><a href="#l295"></a>
<span id="l296">     *</span><a href="#l296"></a>
<span id="l297">     * @param      protocol   the name of the protocol to use.</span><a href="#l297"></a>
<span id="l298">     * @param      host       the name of the host.</span><a href="#l298"></a>
<span id="l299">     * @param      port       the port number on the host.</span><a href="#l299"></a>
<span id="l300">     * @param      file       the file on the host</span><a href="#l300"></a>
<span id="l301">     * @exception  MalformedURLException  if an unknown protocol is specified.</span><a href="#l301"></a>
<span id="l302">     * @see        java.lang.System#getProperty(java.lang.String)</span><a href="#l302"></a>
<span id="l303">     * @see        java.net.URL#setURLStreamHandlerFactory(</span><a href="#l303"></a>
<span id="l304">     *                  java.net.URLStreamHandlerFactory)</span><a href="#l304"></a>
<span id="l305">     * @see        java.net.URLStreamHandler</span><a href="#l305"></a>
<span id="l306">     * @see        java.net.URLStreamHandlerFactory#createURLStreamHandler(</span><a href="#l306"></a>
<span id="l307">     *                  java.lang.String)</span><a href="#l307"></a>
<span id="l308">     */</span><a href="#l308"></a>
<span id="l309">    public URL(String protocol, String host, int port, String file)</span><a href="#l309"></a>
<span id="l310">        throws MalformedURLException</span><a href="#l310"></a>
<span id="l311">    {</span><a href="#l311"></a>
<span id="l312">        this(protocol, host, port, file, null);</span><a href="#l312"></a>
<span id="l313">    }</span><a href="#l313"></a>
<span id="l314"></span><a href="#l314"></a>
<span id="l315">    /**</span><a href="#l315"></a>
<span id="l316">     * Creates a URL from the specified {@code protocol}</span><a href="#l316"></a>
<span id="l317">     * name, {@code host} name, and {@code file} name. The</span><a href="#l317"></a>
<span id="l318">     * default port for the specified protocol is used.</span><a href="#l318"></a>
<span id="l319">     * &lt;p&gt;</span><a href="#l319"></a>
<span id="l320">     * This method is equivalent to calling the four-argument</span><a href="#l320"></a>
<span id="l321">     * constructor with the arguments being {@code protocol},</span><a href="#l321"></a>
<span id="l322">     * {@code host}, {@code -1}, and {@code file}.</span><a href="#l322"></a>
<span id="l323">     *</span><a href="#l323"></a>
<span id="l324">     * No validation of the inputs is performed by this constructor.</span><a href="#l324"></a>
<span id="l325">     *</span><a href="#l325"></a>
<span id="l326">     * @param      protocol   the name of the protocol to use.</span><a href="#l326"></a>
<span id="l327">     * @param      host       the name of the host.</span><a href="#l327"></a>
<span id="l328">     * @param      file       the file on the host.</span><a href="#l328"></a>
<span id="l329">     * @exception  MalformedURLException  if an unknown protocol is specified.</span><a href="#l329"></a>
<span id="l330">     * @see        java.net.URL#URL(java.lang.String, java.lang.String,</span><a href="#l330"></a>
<span id="l331">     *                  int, java.lang.String)</span><a href="#l331"></a>
<span id="l332">     */</span><a href="#l332"></a>
<span id="l333">    public URL(String protocol, String host, String file)</span><a href="#l333"></a>
<span id="l334">            throws MalformedURLException {</span><a href="#l334"></a>
<span id="l335">        this(protocol, host, -1, file);</span><a href="#l335"></a>
<span id="l336">    }</span><a href="#l336"></a>
<span id="l337"></span><a href="#l337"></a>
<span id="l338">    /**</span><a href="#l338"></a>
<span id="l339">     * Creates a {@code URL} object from the specified</span><a href="#l339"></a>
<span id="l340">     * {@code protocol}, {@code host}, {@code port}</span><a href="#l340"></a>
<span id="l341">     * number, {@code file}, and {@code handler}. Specifying</span><a href="#l341"></a>
<span id="l342">     * a {@code port} number of {@code -1} indicates that</span><a href="#l342"></a>
<span id="l343">     * the URL should use the default port for the protocol. Specifying</span><a href="#l343"></a>
<span id="l344">     * a {@code handler} of {@code null} indicates that the URL</span><a href="#l344"></a>
<span id="l345">     * should use a default stream handler for the protocol, as outlined</span><a href="#l345"></a>
<span id="l346">     * for:</span><a href="#l346"></a>
<span id="l347">     *     java.net.URL#URL(java.lang.String, java.lang.String, int,</span><a href="#l347"></a>
<span id="l348">     *                      java.lang.String)</span><a href="#l348"></a>
<span id="l349">     *</span><a href="#l349"></a>
<span id="l350">     * &lt;p&gt;If the handler is not null and there is a security manager,</span><a href="#l350"></a>
<span id="l351">     * the security manager's {@code checkPermission}</span><a href="#l351"></a>
<span id="l352">     * method is called with a</span><a href="#l352"></a>
<span id="l353">     * {@code NetPermission(&quot;specifyStreamHandler&quot;)} permission.</span><a href="#l353"></a>
<span id="l354">     * This may result in a SecurityException.</span><a href="#l354"></a>
<span id="l355">     *</span><a href="#l355"></a>
<span id="l356">     * No validation of the inputs is performed by this constructor.</span><a href="#l356"></a>
<span id="l357">     *</span><a href="#l357"></a>
<span id="l358">     * @param      protocol   the name of the protocol to use.</span><a href="#l358"></a>
<span id="l359">     * @param      host       the name of the host.</span><a href="#l359"></a>
<span id="l360">     * @param      port       the port number on the host.</span><a href="#l360"></a>
<span id="l361">     * @param      file       the file on the host</span><a href="#l361"></a>
<span id="l362">     * @param      handler    the stream handler for the URL.</span><a href="#l362"></a>
<span id="l363">     * @exception  MalformedURLException  if an unknown protocol is specified.</span><a href="#l363"></a>
<span id="l364">     * @exception  SecurityException</span><a href="#l364"></a>
<span id="l365">     *        if a security manager exists and its</span><a href="#l365"></a>
<span id="l366">     *        {@code checkPermission} method doesn't allow</span><a href="#l366"></a>
<span id="l367">     *        specifying a stream handler explicitly.</span><a href="#l367"></a>
<span id="l368">     * @see        java.lang.System#getProperty(java.lang.String)</span><a href="#l368"></a>
<span id="l369">     * @see        java.net.URL#setURLStreamHandlerFactory(</span><a href="#l369"></a>
<span id="l370">     *                  java.net.URLStreamHandlerFactory)</span><a href="#l370"></a>
<span id="l371">     * @see        java.net.URLStreamHandler</span><a href="#l371"></a>
<span id="l372">     * @see        java.net.URLStreamHandlerFactory#createURLStreamHandler(</span><a href="#l372"></a>
<span id="l373">     *                  java.lang.String)</span><a href="#l373"></a>
<span id="l374">     * @see        SecurityManager#checkPermission</span><a href="#l374"></a>
<span id="l375">     * @see        java.net.NetPermission</span><a href="#l375"></a>
<span id="l376">     */</span><a href="#l376"></a>
<span id="l377">    public URL(String protocol, String host, int port, String file,</span><a href="#l377"></a>
<span id="l378">               URLStreamHandler handler) throws MalformedURLException {</span><a href="#l378"></a>
<span id="l379">        if (handler != null) {</span><a href="#l379"></a>
<span id="l380">            SecurityManager sm = System.getSecurityManager();</span><a href="#l380"></a>
<span id="l381">            if (sm != null) {</span><a href="#l381"></a>
<span id="l382">                // check for permission to specify a handler</span><a href="#l382"></a>
<span id="l383">                checkSpecifyHandler(sm);</span><a href="#l383"></a>
<span id="l384">            }</span><a href="#l384"></a>
<span id="l385">        }</span><a href="#l385"></a>
<span id="l386"></span><a href="#l386"></a>
<span id="l387">        protocol = protocol.toLowerCase();</span><a href="#l387"></a>
<span id="l388">        this.protocol = protocol;</span><a href="#l388"></a>
<span id="l389">        if (host != null) {</span><a href="#l389"></a>
<span id="l390"></span><a href="#l390"></a>
<span id="l391">            /**</span><a href="#l391"></a>
<span id="l392">             * if host is a literal IPv6 address,</span><a href="#l392"></a>
<span id="l393">             * we will make it conform to RFC 2732</span><a href="#l393"></a>
<span id="l394">             */</span><a href="#l394"></a>
<span id="l395">            if (host.indexOf(':') &gt;= 0 &amp;&amp; !host.startsWith(&quot;[&quot;)) {</span><a href="#l395"></a>
<span id="l396">                host = &quot;[&quot;+host+&quot;]&quot;;</span><a href="#l396"></a>
<span id="l397">            }</span><a href="#l397"></a>
<span id="l398">            this.host = host;</span><a href="#l398"></a>
<span id="l399"></span><a href="#l399"></a>
<span id="l400">            if (port &lt; -1) {</span><a href="#l400"></a>
<span id="l401">                throw new MalformedURLException(&quot;Invalid port number :&quot; +</span><a href="#l401"></a>
<span id="l402">                                                    port);</span><a href="#l402"></a>
<span id="l403">            }</span><a href="#l403"></a>
<span id="l404">            this.port = port;</span><a href="#l404"></a>
<span id="l405">            authority = (port == -1) ? host : host + &quot;:&quot; + port;</span><a href="#l405"></a>
<span id="l406">        }</span><a href="#l406"></a>
<span id="l407"></span><a href="#l407"></a>
<span id="l408">        Parts parts = new Parts(file);</span><a href="#l408"></a>
<span id="l409">        path = parts.getPath();</span><a href="#l409"></a>
<span id="l410">        query = parts.getQuery();</span><a href="#l410"></a>
<span id="l411"></span><a href="#l411"></a>
<span id="l412">        if (query != null) {</span><a href="#l412"></a>
<span id="l413">            this.file = path + &quot;?&quot; + query;</span><a href="#l413"></a>
<span id="l414">        } else {</span><a href="#l414"></a>
<span id="l415">            this.file = path;</span><a href="#l415"></a>
<span id="l416">        }</span><a href="#l416"></a>
<span id="l417">        ref = parts.getRef();</span><a href="#l417"></a>
<span id="l418"></span><a href="#l418"></a>
<span id="l419">        // Note: we don't do full validation of the URL here. Too risky to change</span><a href="#l419"></a>
<span id="l420">        // right now, but worth considering for future reference. -br</span><a href="#l420"></a>
<span id="l421">        if (handler == null &amp;&amp;</span><a href="#l421"></a>
<span id="l422">            (handler = getURLStreamHandler(protocol)) == null) {</span><a href="#l422"></a>
<span id="l423">            throw new MalformedURLException(&quot;unknown protocol: &quot; + protocol);</span><a href="#l423"></a>
<span id="l424">        }</span><a href="#l424"></a>
<span id="l425">        this.handler = handler;</span><a href="#l425"></a>
<span id="l426">        if (host != null &amp;&amp; isBuiltinStreamHandler(handler)) {</span><a href="#l426"></a>
<span id="l427">            String s = IPAddressUtil.checkExternalForm(this);</span><a href="#l427"></a>
<span id="l428">            if (s != null) {</span><a href="#l428"></a>
<span id="l429">                throw new MalformedURLException(s);</span><a href="#l429"></a>
<span id="l430">            }</span><a href="#l430"></a>
<span id="l431">        }</span><a href="#l431"></a>
<span id="l432">        if (&quot;jar&quot;.equalsIgnoreCase(protocol)) {</span><a href="#l432"></a>
<span id="l433">            if (handler instanceof sun.net.www.protocol.jar.Handler) {</span><a href="#l433"></a>
<span id="l434">                // URL.openConnection() would throw a confusing exception</span><a href="#l434"></a>
<span id="l435">                // so generate a better exception here instead.</span><a href="#l435"></a>
<span id="l436">                String s = ((sun.net.www.protocol.jar.Handler) handler).checkNestedProtocol(file);</span><a href="#l436"></a>
<span id="l437">                if (s != null) {</span><a href="#l437"></a>
<span id="l438">                    throw new MalformedURLException(s);</span><a href="#l438"></a>
<span id="l439">                }</span><a href="#l439"></a>
<span id="l440">            }</span><a href="#l440"></a>
<span id="l441">        }</span><a href="#l441"></a>
<span id="l442">    }</span><a href="#l442"></a>
<span id="l443"></span><a href="#l443"></a>
<span id="l444">    /**</span><a href="#l444"></a>
<span id="l445">     * Creates a {@code URL} object from the {@code String}</span><a href="#l445"></a>
<span id="l446">     * representation.</span><a href="#l446"></a>
<span id="l447">     * &lt;p&gt;</span><a href="#l447"></a>
<span id="l448">     * This constructor is equivalent to a call to the two-argument</span><a href="#l448"></a>
<span id="l449">     * constructor with a {@code null} first argument.</span><a href="#l449"></a>
<span id="l450">     *</span><a href="#l450"></a>
<span id="l451">     * @param      spec   the {@code String} to parse as a URL.</span><a href="#l451"></a>
<span id="l452">     * @exception  MalformedURLException  if no protocol is specified, or an</span><a href="#l452"></a>
<span id="l453">     *               unknown protocol is found, or {@code spec} is {@code null}.</span><a href="#l453"></a>
<span id="l454">     * @see        java.net.URL#URL(java.net.URL, java.lang.String)</span><a href="#l454"></a>
<span id="l455">     */</span><a href="#l455"></a>
<span id="l456">    public URL(String spec) throws MalformedURLException {</span><a href="#l456"></a>
<span id="l457">        this(null, spec);</span><a href="#l457"></a>
<span id="l458">    }</span><a href="#l458"></a>
<span id="l459"></span><a href="#l459"></a>
<span id="l460">    /**</span><a href="#l460"></a>
<span id="l461">     * Creates a URL by parsing the given spec within a specified context.</span><a href="#l461"></a>
<span id="l462">     *</span><a href="#l462"></a>
<span id="l463">     * The new URL is created from the given context URL and the spec</span><a href="#l463"></a>
<span id="l464">     * argument as described in</span><a href="#l464"></a>
<span id="l465">     * RFC2396 &amp;quot;Uniform Resource Identifiers : Generic * Syntax&amp;quot; :</span><a href="#l465"></a>
<span id="l466">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l466"></a>
<span id="l467">     *          &amp;lt;scheme&amp;gt;://&amp;lt;authority&amp;gt;&amp;lt;path&amp;gt;?&amp;lt;query&amp;gt;#&amp;lt;fragment&amp;gt;</span><a href="#l467"></a>
<span id="l468">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l468"></a>
<span id="l469">     * The reference is parsed into the scheme, authority, path, query and</span><a href="#l469"></a>
<span id="l470">     * fragment parts. If the path component is empty and the scheme,</span><a href="#l470"></a>
<span id="l471">     * authority, and query components are undefined, then the new URL is a</span><a href="#l471"></a>
<span id="l472">     * reference to the current document. Otherwise, the fragment and query</span><a href="#l472"></a>
<span id="l473">     * parts present in the spec are used in the new URL.</span><a href="#l473"></a>
<span id="l474">     * &lt;p&gt;</span><a href="#l474"></a>
<span id="l475">     * If the scheme component is defined in the given spec and does not match</span><a href="#l475"></a>
<span id="l476">     * the scheme of the context, then the new URL is created as an absolute</span><a href="#l476"></a>
<span id="l477">     * URL based on the spec alone. Otherwise the scheme component is inherited</span><a href="#l477"></a>
<span id="l478">     * from the context URL.</span><a href="#l478"></a>
<span id="l479">     * &lt;p&gt;</span><a href="#l479"></a>
<span id="l480">     * If the authority component is present in the spec then the spec is</span><a href="#l480"></a>
<span id="l481">     * treated as absolute and the spec authority and path will replace the</span><a href="#l481"></a>
<span id="l482">     * context authority and path. If the authority component is absent in the</span><a href="#l482"></a>
<span id="l483">     * spec then the authority of the new URL will be inherited from the</span><a href="#l483"></a>
<span id="l484">     * context.</span><a href="#l484"></a>
<span id="l485">     * &lt;p&gt;</span><a href="#l485"></a>
<span id="l486">     * If the spec's path component begins with a slash character</span><a href="#l486"></a>
<span id="l487">     * &amp;quot;/&amp;quot; then the</span><a href="#l487"></a>
<span id="l488">     * path is treated as absolute and the spec path replaces the context path.</span><a href="#l488"></a>
<span id="l489">     * &lt;p&gt;</span><a href="#l489"></a>
<span id="l490">     * Otherwise, the path is treated as a relative path and is appended to the</span><a href="#l490"></a>
<span id="l491">     * context path, as described in RFC2396. Also, in this case,</span><a href="#l491"></a>
<span id="l492">     * the path is canonicalized through the removal of directory</span><a href="#l492"></a>
<span id="l493">     * changes made by occurrences of &amp;quot;..&amp;quot; and &amp;quot;.&amp;quot;.</span><a href="#l493"></a>
<span id="l494">     * &lt;p&gt;</span><a href="#l494"></a>
<span id="l495">     * For a more detailed description of URL parsing, refer to RFC2396.</span><a href="#l495"></a>
<span id="l496">     *</span><a href="#l496"></a>
<span id="l497">     * @param      context   the context in which to parse the specification.</span><a href="#l497"></a>
<span id="l498">     * @param      spec      the {@code String} to parse as a URL.</span><a href="#l498"></a>
<span id="l499">     * @exception  MalformedURLException  if no protocol is specified, or an</span><a href="#l499"></a>
<span id="l500">     *               unknown protocol is found, or {@code spec} is {@code null}.</span><a href="#l500"></a>
<span id="l501">     * @see        java.net.URL#URL(java.lang.String, java.lang.String,</span><a href="#l501"></a>
<span id="l502">     *                  int, java.lang.String)</span><a href="#l502"></a>
<span id="l503">     * @see        java.net.URLStreamHandler</span><a href="#l503"></a>
<span id="l504">     * @see        java.net.URLStreamHandler#parseURL(java.net.URL,</span><a href="#l504"></a>
<span id="l505">     *                  java.lang.String, int, int)</span><a href="#l505"></a>
<span id="l506">     */</span><a href="#l506"></a>
<span id="l507">    public URL(URL context, String spec) throws MalformedURLException {</span><a href="#l507"></a>
<span id="l508">        this(context, spec, null);</span><a href="#l508"></a>
<span id="l509">    }</span><a href="#l509"></a>
<span id="l510"></span><a href="#l510"></a>
<span id="l511">    /**</span><a href="#l511"></a>
<span id="l512">     * Creates a URL by parsing the given spec with the specified handler</span><a href="#l512"></a>
<span id="l513">     * within a specified context. If the handler is null, the parsing</span><a href="#l513"></a>
<span id="l514">     * occurs as with the two argument constructor.</span><a href="#l514"></a>
<span id="l515">     *</span><a href="#l515"></a>
<span id="l516">     * @param      context   the context in which to parse the specification.</span><a href="#l516"></a>
<span id="l517">     * @param      spec      the {@code String} to parse as a URL.</span><a href="#l517"></a>
<span id="l518">     * @param      handler   the stream handler for the URL.</span><a href="#l518"></a>
<span id="l519">     * @exception  MalformedURLException  if no protocol is specified, or an</span><a href="#l519"></a>
<span id="l520">     *               unknown protocol is found, or {@code spec} is {@code null}.</span><a href="#l520"></a>
<span id="l521">     * @exception  SecurityException</span><a href="#l521"></a>
<span id="l522">     *        if a security manager exists and its</span><a href="#l522"></a>
<span id="l523">     *        {@code checkPermission} method doesn't allow</span><a href="#l523"></a>
<span id="l524">     *        specifying a stream handler.</span><a href="#l524"></a>
<span id="l525">     * @see        java.net.URL#URL(java.lang.String, java.lang.String,</span><a href="#l525"></a>
<span id="l526">     *                  int, java.lang.String)</span><a href="#l526"></a>
<span id="l527">     * @see        java.net.URLStreamHandler</span><a href="#l527"></a>
<span id="l528">     * @see        java.net.URLStreamHandler#parseURL(java.net.URL,</span><a href="#l528"></a>
<span id="l529">     *                  java.lang.String, int, int)</span><a href="#l529"></a>
<span id="l530">     */</span><a href="#l530"></a>
<span id="l531">    public URL(URL context, String spec, URLStreamHandler handler)</span><a href="#l531"></a>
<span id="l532">        throws MalformedURLException</span><a href="#l532"></a>
<span id="l533">    {</span><a href="#l533"></a>
<span id="l534">        String original = spec;</span><a href="#l534"></a>
<span id="l535">        int i, limit, c;</span><a href="#l535"></a>
<span id="l536">        int start = 0;</span><a href="#l536"></a>
<span id="l537">        String newProtocol = null;</span><a href="#l537"></a>
<span id="l538">        boolean aRef=false;</span><a href="#l538"></a>
<span id="l539">        boolean isRelative = false;</span><a href="#l539"></a>
<span id="l540"></span><a href="#l540"></a>
<span id="l541">        // Check for permission to specify a handler</span><a href="#l541"></a>
<span id="l542">        if (handler != null) {</span><a href="#l542"></a>
<span id="l543">            SecurityManager sm = System.getSecurityManager();</span><a href="#l543"></a>
<span id="l544">            if (sm != null) {</span><a href="#l544"></a>
<span id="l545">                checkSpecifyHandler(sm);</span><a href="#l545"></a>
<span id="l546">            }</span><a href="#l546"></a>
<span id="l547">        }</span><a href="#l547"></a>
<span id="l548"></span><a href="#l548"></a>
<span id="l549">        try {</span><a href="#l549"></a>
<span id="l550">            limit = spec.length();</span><a href="#l550"></a>
<span id="l551">            while ((limit &gt; 0) &amp;&amp; (spec.charAt(limit - 1) &lt;= ' ')) {</span><a href="#l551"></a>
<span id="l552">                limit--;        //eliminate trailing whitespace</span><a href="#l552"></a>
<span id="l553">            }</span><a href="#l553"></a>
<span id="l554">            while ((start &lt; limit) &amp;&amp; (spec.charAt(start) &lt;= ' ')) {</span><a href="#l554"></a>
<span id="l555">                start++;        // eliminate leading whitespace</span><a href="#l555"></a>
<span id="l556">            }</span><a href="#l556"></a>
<span id="l557"></span><a href="#l557"></a>
<span id="l558">            if (spec.regionMatches(true, start, &quot;url:&quot;, 0, 4)) {</span><a href="#l558"></a>
<span id="l559">                start += 4;</span><a href="#l559"></a>
<span id="l560">            }</span><a href="#l560"></a>
<span id="l561">            if (start &lt; spec.length() &amp;&amp; spec.charAt(start) == '#') {</span><a href="#l561"></a>
<span id="l562">                /* we're assuming this is a ref relative to the context URL.</span><a href="#l562"></a>
<span id="l563">                 * This means protocols cannot start w/ '#', but we must parse</span><a href="#l563"></a>
<span id="l564">                 * ref URL's like: &quot;hello:there&quot; w/ a ':' in them.</span><a href="#l564"></a>
<span id="l565">                 */</span><a href="#l565"></a>
<span id="l566">                aRef=true;</span><a href="#l566"></a>
<span id="l567">            }</span><a href="#l567"></a>
<span id="l568">            for (i = start ; !aRef &amp;&amp; (i &lt; limit) &amp;&amp;</span><a href="#l568"></a>
<span id="l569">                     ((c = spec.charAt(i)) != '/') ; i++) {</span><a href="#l569"></a>
<span id="l570">                if (c == ':') {</span><a href="#l570"></a>
<span id="l571"></span><a href="#l571"></a>
<span id="l572">                    String s = spec.substring(start, i).toLowerCase();</span><a href="#l572"></a>
<span id="l573">                    if (isValidProtocol(s)) {</span><a href="#l573"></a>
<span id="l574">                        newProtocol = s;</span><a href="#l574"></a>
<span id="l575">                        start = i + 1;</span><a href="#l575"></a>
<span id="l576">                    }</span><a href="#l576"></a>
<span id="l577">                    break;</span><a href="#l577"></a>
<span id="l578">                }</span><a href="#l578"></a>
<span id="l579">            }</span><a href="#l579"></a>
<span id="l580"></span><a href="#l580"></a>
<span id="l581">            // Only use our context if the protocols match.</span><a href="#l581"></a>
<span id="l582">            protocol = newProtocol;</span><a href="#l582"></a>
<span id="l583">            if ((context != null) &amp;&amp; ((newProtocol == null) ||</span><a href="#l583"></a>
<span id="l584">                            newProtocol.equalsIgnoreCase(context.protocol))) {</span><a href="#l584"></a>
<span id="l585">                // inherit the protocol handler from the context</span><a href="#l585"></a>
<span id="l586">                // if not specified to the constructor</span><a href="#l586"></a>
<span id="l587">                if (handler == null) {</span><a href="#l587"></a>
<span id="l588">                    handler = context.handler;</span><a href="#l588"></a>
<span id="l589">                }</span><a href="#l589"></a>
<span id="l590"></span><a href="#l590"></a>
<span id="l591">                // If the context is a hierarchical URL scheme and the spec</span><a href="#l591"></a>
<span id="l592">                // contains a matching scheme then maintain backwards</span><a href="#l592"></a>
<span id="l593">                // compatibility and treat it as if the spec didn't contain</span><a href="#l593"></a>
<span id="l594">                // the scheme; see 5.2.3 of RFC2396</span><a href="#l594"></a>
<span id="l595">                if (context.path != null &amp;&amp; context.path.startsWith(&quot;/&quot;))</span><a href="#l595"></a>
<span id="l596">                    newProtocol = null;</span><a href="#l596"></a>
<span id="l597"></span><a href="#l597"></a>
<span id="l598">                if (newProtocol == null) {</span><a href="#l598"></a>
<span id="l599">                    protocol = context.protocol;</span><a href="#l599"></a>
<span id="l600">                    authority = context.authority;</span><a href="#l600"></a>
<span id="l601">                    userInfo = context.userInfo;</span><a href="#l601"></a>
<span id="l602">                    host = context.host;</span><a href="#l602"></a>
<span id="l603">                    port = context.port;</span><a href="#l603"></a>
<span id="l604">                    file = context.file;</span><a href="#l604"></a>
<span id="l605">                    path = context.path;</span><a href="#l605"></a>
<span id="l606">                    isRelative = true;</span><a href="#l606"></a>
<span id="l607">                }</span><a href="#l607"></a>
<span id="l608">            }</span><a href="#l608"></a>
<span id="l609"></span><a href="#l609"></a>
<span id="l610">            if (protocol == null) {</span><a href="#l610"></a>
<span id="l611">                throw new MalformedURLException(&quot;no protocol: &quot;+original);</span><a href="#l611"></a>
<span id="l612">            }</span><a href="#l612"></a>
<span id="l613"></span><a href="#l613"></a>
<span id="l614">            // Get the protocol handler if not specified or the protocol</span><a href="#l614"></a>
<span id="l615">            // of the context could not be used</span><a href="#l615"></a>
<span id="l616">            if (handler == null &amp;&amp;</span><a href="#l616"></a>
<span id="l617">                (handler = getURLStreamHandler(protocol)) == null) {</span><a href="#l617"></a>
<span id="l618">                throw new MalformedURLException(&quot;unknown protocol: &quot;+protocol);</span><a href="#l618"></a>
<span id="l619">            }</span><a href="#l619"></a>
<span id="l620"></span><a href="#l620"></a>
<span id="l621">            this.handler = handler;</span><a href="#l621"></a>
<span id="l622"></span><a href="#l622"></a>
<span id="l623">            i = spec.indexOf('#', start);</span><a href="#l623"></a>
<span id="l624">            if (i &gt;= 0) {</span><a href="#l624"></a>
<span id="l625">                ref = spec.substring(i + 1, limit);</span><a href="#l625"></a>
<span id="l626">                limit = i;</span><a href="#l626"></a>
<span id="l627">            }</span><a href="#l627"></a>
<span id="l628"></span><a href="#l628"></a>
<span id="l629">            /*</span><a href="#l629"></a>
<span id="l630">             * Handle special case inheritance of query and fragment</span><a href="#l630"></a>
<span id="l631">             * implied by RFC2396 section 5.2.2.</span><a href="#l631"></a>
<span id="l632">             */</span><a href="#l632"></a>
<span id="l633">            if (isRelative &amp;&amp; start == limit) {</span><a href="#l633"></a>
<span id="l634">                query = context.query;</span><a href="#l634"></a>
<span id="l635">                if (ref == null) {</span><a href="#l635"></a>
<span id="l636">                    ref = context.ref;</span><a href="#l636"></a>
<span id="l637">                }</span><a href="#l637"></a>
<span id="l638">            }</span><a href="#l638"></a>
<span id="l639"></span><a href="#l639"></a>
<span id="l640">            handler.parseURL(this, spec, start, limit);</span><a href="#l640"></a>
<span id="l641"></span><a href="#l641"></a>
<span id="l642">        } catch(MalformedURLException e) {</span><a href="#l642"></a>
<span id="l643">            throw e;</span><a href="#l643"></a>
<span id="l644">        } catch(Exception e) {</span><a href="#l644"></a>
<span id="l645">            MalformedURLException exception = new MalformedURLException(e.getMessage());</span><a href="#l645"></a>
<span id="l646">            exception.initCause(e);</span><a href="#l646"></a>
<span id="l647">            throw exception;</span><a href="#l647"></a>
<span id="l648">        }</span><a href="#l648"></a>
<span id="l649">    }</span><a href="#l649"></a>
<span id="l650"></span><a href="#l650"></a>
<span id="l651">    /*</span><a href="#l651"></a>
<span id="l652">     * Returns true if specified string is a valid protocol name.</span><a href="#l652"></a>
<span id="l653">     */</span><a href="#l653"></a>
<span id="l654">    private boolean isValidProtocol(String protocol) {</span><a href="#l654"></a>
<span id="l655">        int len = protocol.length();</span><a href="#l655"></a>
<span id="l656">        if (len &lt; 1)</span><a href="#l656"></a>
<span id="l657">            return false;</span><a href="#l657"></a>
<span id="l658">        char c = protocol.charAt(0);</span><a href="#l658"></a>
<span id="l659">        if (!Character.isLetter(c))</span><a href="#l659"></a>
<span id="l660">            return false;</span><a href="#l660"></a>
<span id="l661">        for (int i = 1; i &lt; len; i++) {</span><a href="#l661"></a>
<span id="l662">            c = protocol.charAt(i);</span><a href="#l662"></a>
<span id="l663">            if (!Character.isLetterOrDigit(c) &amp;&amp; c != '.' &amp;&amp; c != '+' &amp;&amp;</span><a href="#l663"></a>
<span id="l664">                c != '-') {</span><a href="#l664"></a>
<span id="l665">                return false;</span><a href="#l665"></a>
<span id="l666">            }</span><a href="#l666"></a>
<span id="l667">        }</span><a href="#l667"></a>
<span id="l668">        return true;</span><a href="#l668"></a>
<span id="l669">    }</span><a href="#l669"></a>
<span id="l670"></span><a href="#l670"></a>
<span id="l671">    /*</span><a href="#l671"></a>
<span id="l672">     * Checks for permission to specify a stream handler.</span><a href="#l672"></a>
<span id="l673">     */</span><a href="#l673"></a>
<span id="l674">    private void checkSpecifyHandler(SecurityManager sm) {</span><a href="#l674"></a>
<span id="l675">        sm.checkPermission(SecurityConstants.SPECIFY_HANDLER_PERMISSION);</span><a href="#l675"></a>
<span id="l676">    }</span><a href="#l676"></a>
<span id="l677"></span><a href="#l677"></a>
<span id="l678">    /**</span><a href="#l678"></a>
<span id="l679">     * Sets the fields of the URL. This is not a public method so that</span><a href="#l679"></a>
<span id="l680">     * only URLStreamHandlers can modify URL fields. URLs are</span><a href="#l680"></a>
<span id="l681">     * otherwise constant.</span><a href="#l681"></a>
<span id="l682">     *</span><a href="#l682"></a>
<span id="l683">     * @param protocol the name of the protocol to use</span><a href="#l683"></a>
<span id="l684">     * @param host the name of the host</span><a href="#l684"></a>
<span id="l685">       @param port the port number on the host</span><a href="#l685"></a>
<span id="l686">     * @param file the file on the host</span><a href="#l686"></a>
<span id="l687">     * @param ref the internal reference in the URL</span><a href="#l687"></a>
<span id="l688">     */</span><a href="#l688"></a>
<span id="l689">    void set(String protocol, String host, int port,</span><a href="#l689"></a>
<span id="l690">             String file, String ref) {</span><a href="#l690"></a>
<span id="l691">        synchronized (this) {</span><a href="#l691"></a>
<span id="l692">            this.protocol = protocol;</span><a href="#l692"></a>
<span id="l693">            this.host = host;</span><a href="#l693"></a>
<span id="l694">            authority = port == -1 ? host : host + &quot;:&quot; + port;</span><a href="#l694"></a>
<span id="l695">            this.port = port;</span><a href="#l695"></a>
<span id="l696">            this.file = file;</span><a href="#l696"></a>
<span id="l697">            this.ref = ref;</span><a href="#l697"></a>
<span id="l698">            /* This is very important. We must recompute this after the</span><a href="#l698"></a>
<span id="l699">             * URL has been changed. */</span><a href="#l699"></a>
<span id="l700">            hashCode = -1;</span><a href="#l700"></a>
<span id="l701">            hostAddress = null;</span><a href="#l701"></a>
<span id="l702">            int q = file.lastIndexOf('?');</span><a href="#l702"></a>
<span id="l703">            if (q != -1) {</span><a href="#l703"></a>
<span id="l704">                query = file.substring(q+1);</span><a href="#l704"></a>
<span id="l705">                path = file.substring(0, q);</span><a href="#l705"></a>
<span id="l706">            } else</span><a href="#l706"></a>
<span id="l707">                path = file;</span><a href="#l707"></a>
<span id="l708">        }</span><a href="#l708"></a>
<span id="l709">    }</span><a href="#l709"></a>
<span id="l710"></span><a href="#l710"></a>
<span id="l711">    /**</span><a href="#l711"></a>
<span id="l712">     * Sets the specified 8 fields of the URL. This is not a public method so</span><a href="#l712"></a>
<span id="l713">     * that only URLStreamHandlers can modify URL fields. URLs are otherwise</span><a href="#l713"></a>
<span id="l714">     * constant.</span><a href="#l714"></a>
<span id="l715">     *</span><a href="#l715"></a>
<span id="l716">     * @param protocol the name of the protocol to use</span><a href="#l716"></a>
<span id="l717">     * @param host the name of the host</span><a href="#l717"></a>
<span id="l718">     * @param port the port number on the host</span><a href="#l718"></a>
<span id="l719">     * @param authority the authority part for the url</span><a href="#l719"></a>
<span id="l720">     * @param userInfo the username and password</span><a href="#l720"></a>
<span id="l721">     * @param path the file on the host</span><a href="#l721"></a>
<span id="l722">     * @param ref the internal reference in the URL</span><a href="#l722"></a>
<span id="l723">     * @param query the query part of this URL</span><a href="#l723"></a>
<span id="l724">     * @since 1.3</span><a href="#l724"></a>
<span id="l725">     */</span><a href="#l725"></a>
<span id="l726">    void set(String protocol, String host, int port,</span><a href="#l726"></a>
<span id="l727">             String authority, String userInfo, String path,</span><a href="#l727"></a>
<span id="l728">             String query, String ref) {</span><a href="#l728"></a>
<span id="l729">        synchronized (this) {</span><a href="#l729"></a>
<span id="l730">            this.protocol = protocol;</span><a href="#l730"></a>
<span id="l731">            this.host = host;</span><a href="#l731"></a>
<span id="l732">            this.port = port;</span><a href="#l732"></a>
<span id="l733">            this.file = query == null ? path : path + &quot;?&quot; + query;</span><a href="#l733"></a>
<span id="l734">            this.userInfo = userInfo;</span><a href="#l734"></a>
<span id="l735">            this.path = path;</span><a href="#l735"></a>
<span id="l736">            this.ref = ref;</span><a href="#l736"></a>
<span id="l737">            /* This is very important. We must recompute this after the</span><a href="#l737"></a>
<span id="l738">             * URL has been changed. */</span><a href="#l738"></a>
<span id="l739">            hashCode = -1;</span><a href="#l739"></a>
<span id="l740">            hostAddress = null;</span><a href="#l740"></a>
<span id="l741">            this.query = query;</span><a href="#l741"></a>
<span id="l742">            this.authority = authority;</span><a href="#l742"></a>
<span id="l743">        }</span><a href="#l743"></a>
<span id="l744">    }</span><a href="#l744"></a>
<span id="l745"></span><a href="#l745"></a>
<span id="l746">    /**</span><a href="#l746"></a>
<span id="l747">     * Gets the query part of this {@code URL}.</span><a href="#l747"></a>
<span id="l748">     *</span><a href="#l748"></a>
<span id="l749">     * @return  the query part of this {@code URL},</span><a href="#l749"></a>
<span id="l750">     * or &lt;CODE&gt;null&lt;/CODE&gt; if one does not exist</span><a href="#l750"></a>
<span id="l751">     * @since 1.3</span><a href="#l751"></a>
<span id="l752">     */</span><a href="#l752"></a>
<span id="l753">    public String getQuery() {</span><a href="#l753"></a>
<span id="l754">        return query;</span><a href="#l754"></a>
<span id="l755">    }</span><a href="#l755"></a>
<span id="l756"></span><a href="#l756"></a>
<span id="l757">    /**</span><a href="#l757"></a>
<span id="l758">     * Gets the path part of this {@code URL}.</span><a href="#l758"></a>
<span id="l759">     *</span><a href="#l759"></a>
<span id="l760">     * @return  the path part of this {@code URL}, or an</span><a href="#l760"></a>
<span id="l761">     * empty string if one does not exist</span><a href="#l761"></a>
<span id="l762">     * @since 1.3</span><a href="#l762"></a>
<span id="l763">     */</span><a href="#l763"></a>
<span id="l764">    public String getPath() {</span><a href="#l764"></a>
<span id="l765">        return path;</span><a href="#l765"></a>
<span id="l766">    }</span><a href="#l766"></a>
<span id="l767"></span><a href="#l767"></a>
<span id="l768">    /**</span><a href="#l768"></a>
<span id="l769">     * Gets the userInfo part of this {@code URL}.</span><a href="#l769"></a>
<span id="l770">     *</span><a href="#l770"></a>
<span id="l771">     * @return  the userInfo part of this {@code URL}, or</span><a href="#l771"></a>
<span id="l772">     * &lt;CODE&gt;null&lt;/CODE&gt; if one does not exist</span><a href="#l772"></a>
<span id="l773">     * @since 1.3</span><a href="#l773"></a>
<span id="l774">     */</span><a href="#l774"></a>
<span id="l775">    public String getUserInfo() {</span><a href="#l775"></a>
<span id="l776">        return userInfo;</span><a href="#l776"></a>
<span id="l777">    }</span><a href="#l777"></a>
<span id="l778"></span><a href="#l778"></a>
<span id="l779">    /**</span><a href="#l779"></a>
<span id="l780">     * Gets the authority part of this {@code URL}.</span><a href="#l780"></a>
<span id="l781">     *</span><a href="#l781"></a>
<span id="l782">     * @return  the authority part of this {@code URL}</span><a href="#l782"></a>
<span id="l783">     * @since 1.3</span><a href="#l783"></a>
<span id="l784">     */</span><a href="#l784"></a>
<span id="l785">    public String getAuthority() {</span><a href="#l785"></a>
<span id="l786">        return authority;</span><a href="#l786"></a>
<span id="l787">    }</span><a href="#l787"></a>
<span id="l788"></span><a href="#l788"></a>
<span id="l789">    /**</span><a href="#l789"></a>
<span id="l790">     * Gets the port number of this {@code URL}.</span><a href="#l790"></a>
<span id="l791">     *</span><a href="#l791"></a>
<span id="l792">     * @return  the port number, or -1 if the port is not set</span><a href="#l792"></a>
<span id="l793">     */</span><a href="#l793"></a>
<span id="l794">    public int getPort() {</span><a href="#l794"></a>
<span id="l795">        return port;</span><a href="#l795"></a>
<span id="l796">    }</span><a href="#l796"></a>
<span id="l797"></span><a href="#l797"></a>
<span id="l798">    /**</span><a href="#l798"></a>
<span id="l799">     * Gets the default port number of the protocol associated</span><a href="#l799"></a>
<span id="l800">     * with this {@code URL}. If the URL scheme or the URLStreamHandler</span><a href="#l800"></a>
<span id="l801">     * for the URL do not define a default port number,</span><a href="#l801"></a>
<span id="l802">     * then -1 is returned.</span><a href="#l802"></a>
<span id="l803">     *</span><a href="#l803"></a>
<span id="l804">     * @return  the port number</span><a href="#l804"></a>
<span id="l805">     * @since 1.4</span><a href="#l805"></a>
<span id="l806">     */</span><a href="#l806"></a>
<span id="l807">    public int getDefaultPort() {</span><a href="#l807"></a>
<span id="l808">        return handler.getDefaultPort();</span><a href="#l808"></a>
<span id="l809">    }</span><a href="#l809"></a>
<span id="l810"></span><a href="#l810"></a>
<span id="l811">    /**</span><a href="#l811"></a>
<span id="l812">     * Gets the protocol name of this {@code URL}.</span><a href="#l812"></a>
<span id="l813">     *</span><a href="#l813"></a>
<span id="l814">     * @return  the protocol of this {@code URL}.</span><a href="#l814"></a>
<span id="l815">     */</span><a href="#l815"></a>
<span id="l816">    public String getProtocol() {</span><a href="#l816"></a>
<span id="l817">        return protocol;</span><a href="#l817"></a>
<span id="l818">    }</span><a href="#l818"></a>
<span id="l819"></span><a href="#l819"></a>
<span id="l820">    /**</span><a href="#l820"></a>
<span id="l821">     * Gets the host name of this {@code URL}, if applicable.</span><a href="#l821"></a>
<span id="l822">     * The format of the host conforms to RFC 2732, i.e. for a</span><a href="#l822"></a>
<span id="l823">     * literal IPv6 address, this method will return the IPv6 address</span><a href="#l823"></a>
<span id="l824">     * enclosed in square brackets ({@code '['} and {@code ']'}).</span><a href="#l824"></a>
<span id="l825">     *</span><a href="#l825"></a>
<span id="l826">     * @return  the host name of this {@code URL}.</span><a href="#l826"></a>
<span id="l827">     */</span><a href="#l827"></a>
<span id="l828">    public String getHost() {</span><a href="#l828"></a>
<span id="l829">        return host;</span><a href="#l829"></a>
<span id="l830">    }</span><a href="#l830"></a>
<span id="l831"></span><a href="#l831"></a>
<span id="l832">    /**</span><a href="#l832"></a>
<span id="l833">     * Gets the file name of this {@code URL}.</span><a href="#l833"></a>
<span id="l834">     * The returned file portion will be</span><a href="#l834"></a>
<span id="l835">     * the same as &lt;CODE&gt;getPath()&lt;/CODE&gt;, plus the concatenation of</span><a href="#l835"></a>
<span id="l836">     * the value of &lt;CODE&gt;getQuery()&lt;/CODE&gt;, if any. If there is</span><a href="#l836"></a>
<span id="l837">     * no query portion, this method and &lt;CODE&gt;getPath()&lt;/CODE&gt; will</span><a href="#l837"></a>
<span id="l838">     * return identical results.</span><a href="#l838"></a>
<span id="l839">     *</span><a href="#l839"></a>
<span id="l840">     * @return  the file name of this {@code URL},</span><a href="#l840"></a>
<span id="l841">     * or an empty string if one does not exist</span><a href="#l841"></a>
<span id="l842">     */</span><a href="#l842"></a>
<span id="l843">    public String getFile() {</span><a href="#l843"></a>
<span id="l844">        return file;</span><a href="#l844"></a>
<span id="l845">    }</span><a href="#l845"></a>
<span id="l846"></span><a href="#l846"></a>
<span id="l847">    /**</span><a href="#l847"></a>
<span id="l848">     * Gets the anchor (also known as the &quot;reference&quot;) of this</span><a href="#l848"></a>
<span id="l849">     * {@code URL}.</span><a href="#l849"></a>
<span id="l850">     *</span><a href="#l850"></a>
<span id="l851">     * @return  the anchor (also known as the &quot;reference&quot;) of this</span><a href="#l851"></a>
<span id="l852">     *          {@code URL}, or &lt;CODE&gt;null&lt;/CODE&gt; if one does not exist</span><a href="#l852"></a>
<span id="l853">     */</span><a href="#l853"></a>
<span id="l854">    public String getRef() {</span><a href="#l854"></a>
<span id="l855">        return ref;</span><a href="#l855"></a>
<span id="l856">    }</span><a href="#l856"></a>
<span id="l857"></span><a href="#l857"></a>
<span id="l858">    /**</span><a href="#l858"></a>
<span id="l859">     * Compares this URL for equality with another object.&lt;p&gt;</span><a href="#l859"></a>
<span id="l860">     *</span><a href="#l860"></a>
<span id="l861">     * If the given object is not a URL then this method immediately returns</span><a href="#l861"></a>
<span id="l862">     * {@code false}.&lt;p&gt;</span><a href="#l862"></a>
<span id="l863">     *</span><a href="#l863"></a>
<span id="l864">     * Two URL objects are equal if they have the same protocol, reference</span><a href="#l864"></a>
<span id="l865">     * equivalent hosts, have the same port number on the host, and the same</span><a href="#l865"></a>
<span id="l866">     * file and fragment of the file.&lt;p&gt;</span><a href="#l866"></a>
<span id="l867">     *</span><a href="#l867"></a>
<span id="l868">     * Two hosts are considered equivalent if both host names can be resolved</span><a href="#l868"></a>
<span id="l869">     * into the same IP addresses; else if either host name can't be</span><a href="#l869"></a>
<span id="l870">     * resolved, the host names must be equal without regard to case; or both</span><a href="#l870"></a>
<span id="l871">     * host names equal to null.&lt;p&gt;</span><a href="#l871"></a>
<span id="l872">     *</span><a href="#l872"></a>
<span id="l873">     * Since hosts comparison requires name resolution, this operation is a</span><a href="#l873"></a>
<span id="l874">     * blocking operation. &lt;p&gt;</span><a href="#l874"></a>
<span id="l875">     *</span><a href="#l875"></a>
<span id="l876">     * Note: The defined behavior for {@code equals} is known to</span><a href="#l876"></a>
<span id="l877">     * be inconsistent with virtual hosting in HTTP.</span><a href="#l877"></a>
<span id="l878">     *</span><a href="#l878"></a>
<span id="l879">     * @param   obj   the URL to compare against.</span><a href="#l879"></a>
<span id="l880">     * @return  {@code true} if the objects are the same;</span><a href="#l880"></a>
<span id="l881">     *          {@code false} otherwise.</span><a href="#l881"></a>
<span id="l882">     */</span><a href="#l882"></a>
<span id="l883">    public boolean equals(Object obj) {</span><a href="#l883"></a>
<span id="l884">        if (!(obj instanceof URL))</span><a href="#l884"></a>
<span id="l885">            return false;</span><a href="#l885"></a>
<span id="l886">        URL u2 = (URL)obj;</span><a href="#l886"></a>
<span id="l887"></span><a href="#l887"></a>
<span id="l888">        return handler.equals(this, u2);</span><a href="#l888"></a>
<span id="l889">    }</span><a href="#l889"></a>
<span id="l890"></span><a href="#l890"></a>
<span id="l891">    /**</span><a href="#l891"></a>
<span id="l892">     * Creates an integer suitable for hash table indexing.&lt;p&gt;</span><a href="#l892"></a>
<span id="l893">     *</span><a href="#l893"></a>
<span id="l894">     * The hash code is based upon all the URL components relevant for URL</span><a href="#l894"></a>
<span id="l895">     * comparison. As such, this operation is a blocking operation.&lt;p&gt;</span><a href="#l895"></a>
<span id="l896">     *</span><a href="#l896"></a>
<span id="l897">     * @return  a hash code for this {@code URL}.</span><a href="#l897"></a>
<span id="l898">     */</span><a href="#l898"></a>
<span id="l899">    public synchronized int hashCode() {</span><a href="#l899"></a>
<span id="l900">        if (hashCode != -1)</span><a href="#l900"></a>
<span id="l901">            return hashCode;</span><a href="#l901"></a>
<span id="l902"></span><a href="#l902"></a>
<span id="l903">        hashCode = handler.hashCode(this);</span><a href="#l903"></a>
<span id="l904">        return hashCode;</span><a href="#l904"></a>
<span id="l905">    }</span><a href="#l905"></a>
<span id="l906"></span><a href="#l906"></a>
<span id="l907">    /**</span><a href="#l907"></a>
<span id="l908">     * Compares two URLs, excluding the fragment component.&lt;p&gt;</span><a href="#l908"></a>
<span id="l909">     *</span><a href="#l909"></a>
<span id="l910">     * Returns {@code true} if this {@code URL} and the</span><a href="#l910"></a>
<span id="l911">     * {@code other} argument are equal without taking the</span><a href="#l911"></a>
<span id="l912">     * fragment component into consideration.</span><a href="#l912"></a>
<span id="l913">     *</span><a href="#l913"></a>
<span id="l914">     * @param   other   the {@code URL} to compare against.</span><a href="#l914"></a>
<span id="l915">     * @return  {@code true} if they reference the same remote object;</span><a href="#l915"></a>
<span id="l916">     *          {@code false} otherwise.</span><a href="#l916"></a>
<span id="l917">     */</span><a href="#l917"></a>
<span id="l918">    public boolean sameFile(URL other) {</span><a href="#l918"></a>
<span id="l919">        return handler.sameFile(this, other);</span><a href="#l919"></a>
<span id="l920">    }</span><a href="#l920"></a>
<span id="l921"></span><a href="#l921"></a>
<span id="l922">    /**</span><a href="#l922"></a>
<span id="l923">     * Constructs a string representation of this {@code URL}. The</span><a href="#l923"></a>
<span id="l924">     * string is created by calling the {@code toExternalForm}</span><a href="#l924"></a>
<span id="l925">     * method of the stream protocol handler for this object.</span><a href="#l925"></a>
<span id="l926">     *</span><a href="#l926"></a>
<span id="l927">     * @return  a string representation of this object.</span><a href="#l927"></a>
<span id="l928">     * @see     java.net.URL#URL(java.lang.String, java.lang.String, int,</span><a href="#l928"></a>
<span id="l929">     *                  java.lang.String)</span><a href="#l929"></a>
<span id="l930">     * @see     java.net.URLStreamHandler#toExternalForm(java.net.URL)</span><a href="#l930"></a>
<span id="l931">     */</span><a href="#l931"></a>
<span id="l932">    public String toString() {</span><a href="#l932"></a>
<span id="l933">        return toExternalForm();</span><a href="#l933"></a>
<span id="l934">    }</span><a href="#l934"></a>
<span id="l935"></span><a href="#l935"></a>
<span id="l936">    /**</span><a href="#l936"></a>
<span id="l937">     * Constructs a string representation of this {@code URL}. The</span><a href="#l937"></a>
<span id="l938">     * string is created by calling the {@code toExternalForm}</span><a href="#l938"></a>
<span id="l939">     * method of the stream protocol handler for this object.</span><a href="#l939"></a>
<span id="l940">     *</span><a href="#l940"></a>
<span id="l941">     * @return  a string representation of this object.</span><a href="#l941"></a>
<span id="l942">     * @see     java.net.URL#URL(java.lang.String, java.lang.String,</span><a href="#l942"></a>
<span id="l943">     *                  int, java.lang.String)</span><a href="#l943"></a>
<span id="l944">     * @see     java.net.URLStreamHandler#toExternalForm(java.net.URL)</span><a href="#l944"></a>
<span id="l945">     */</span><a href="#l945"></a>
<span id="l946">    public String toExternalForm() {</span><a href="#l946"></a>
<span id="l947">        return handler.toExternalForm(this);</span><a href="#l947"></a>
<span id="l948">    }</span><a href="#l948"></a>
<span id="l949"></span><a href="#l949"></a>
<span id="l950">    /**</span><a href="#l950"></a>
<span id="l951">     * Returns a {@link java.net.URI} equivalent to this URL.</span><a href="#l951"></a>
<span id="l952">     * This method functions in the same way as {@code new URI (this.toString())}.</span><a href="#l952"></a>
<span id="l953">     * &lt;p&gt;Note, any URL instance that complies with RFC 2396 can be converted</span><a href="#l953"></a>
<span id="l954">     * to a URI. However, some URLs that are not strictly in compliance</span><a href="#l954"></a>
<span id="l955">     * can not be converted to a URI.</span><a href="#l955"></a>
<span id="l956">     *</span><a href="#l956"></a>
<span id="l957">     * @exception URISyntaxException if this URL is not formatted strictly according to</span><a href="#l957"></a>
<span id="l958">     *            to RFC2396 and cannot be converted to a URI.</span><a href="#l958"></a>
<span id="l959">     *</span><a href="#l959"></a>
<span id="l960">     * @return    a URI instance equivalent to this URL.</span><a href="#l960"></a>
<span id="l961">     * @since 1.5</span><a href="#l961"></a>
<span id="l962">     */</span><a href="#l962"></a>
<span id="l963">    public URI toURI() throws URISyntaxException {</span><a href="#l963"></a>
<span id="l964">        URI uri = new URI(toString());</span><a href="#l964"></a>
<span id="l965">        if (authority != null &amp;&amp; isBuiltinStreamHandler(handler)) {</span><a href="#l965"></a>
<span id="l966">            String s = IPAddressUtil.checkAuthority(this);</span><a href="#l966"></a>
<span id="l967">            if (s != null) throw new URISyntaxException(authority, s);</span><a href="#l967"></a>
<span id="l968">        }</span><a href="#l968"></a>
<span id="l969">        return uri;</span><a href="#l969"></a>
<span id="l970">    }</span><a href="#l970"></a>
<span id="l971"></span><a href="#l971"></a>
<span id="l972">    /**</span><a href="#l972"></a>
<span id="l973">     * Returns a {@link java.net.URLConnection URLConnection} instance that</span><a href="#l973"></a>
<span id="l974">     * represents a connection to the remote object referred to by the</span><a href="#l974"></a>
<span id="l975">     * {@code URL}.</span><a href="#l975"></a>
<span id="l976">     *</span><a href="#l976"></a>
<span id="l977">     * &lt;P&gt;A new instance of {@linkplain java.net.URLConnection URLConnection} is</span><a href="#l977"></a>
<span id="l978">     * created every time when invoking the</span><a href="#l978"></a>
<span id="l979">     * {@linkplain java.net.URLStreamHandler#openConnection(URL)</span><a href="#l979"></a>
<span id="l980">     * URLStreamHandler.openConnection(URL)} method of the protocol handler for</span><a href="#l980"></a>
<span id="l981">     * this URL.&lt;/P&gt;</span><a href="#l981"></a>
<span id="l982">     *</span><a href="#l982"></a>
<span id="l983">     * &lt;P&gt;It should be noted that a URLConnection instance does not establish</span><a href="#l983"></a>
<span id="l984">     * the actual network connection on creation. This will happen only when</span><a href="#l984"></a>
<span id="l985">     * calling {@linkplain java.net.URLConnection#connect() URLConnection.connect()}.&lt;/P&gt;</span><a href="#l985"></a>
<span id="l986">     *</span><a href="#l986"></a>
<span id="l987">     * &lt;P&gt;If for the URL's protocol (such as HTTP or JAR), there</span><a href="#l987"></a>
<span id="l988">     * exists a public, specialized URLConnection subclass belonging</span><a href="#l988"></a>
<span id="l989">     * to one of the following packages or one of their subpackages:</span><a href="#l989"></a>
<span id="l990">     * java.lang, java.io, java.util, java.net, the connection</span><a href="#l990"></a>
<span id="l991">     * returned will be of that subclass. For example, for HTTP an</span><a href="#l991"></a>
<span id="l992">     * HttpURLConnection will be returned, and for JAR a</span><a href="#l992"></a>
<span id="l993">     * JarURLConnection will be returned.&lt;/P&gt;</span><a href="#l993"></a>
<span id="l994">     *</span><a href="#l994"></a>
<span id="l995">     * @return     a {@link java.net.URLConnection URLConnection} linking</span><a href="#l995"></a>
<span id="l996">     *             to the URL.</span><a href="#l996"></a>
<span id="l997">     * @exception  IOException  if an I/O exception occurs.</span><a href="#l997"></a>
<span id="l998">     * @see        java.net.URL#URL(java.lang.String, java.lang.String,</span><a href="#l998"></a>
<span id="l999">     *             int, java.lang.String)</span><a href="#l999"></a>
<span id="l1000">     */</span><a href="#l1000"></a>
<span id="l1001">    public URLConnection openConnection() throws java.io.IOException {</span><a href="#l1001"></a>
<span id="l1002">        return handler.openConnection(this);</span><a href="#l1002"></a>
<span id="l1003">    }</span><a href="#l1003"></a>
<span id="l1004"></span><a href="#l1004"></a>
<span id="l1005">    /**</span><a href="#l1005"></a>
<span id="l1006">     * Same as {@link #openConnection()}, except that the connection will be</span><a href="#l1006"></a>
<span id="l1007">     * made through the specified proxy; Protocol handlers that do not</span><a href="#l1007"></a>
<span id="l1008">     * support proxing will ignore the proxy parameter and make a</span><a href="#l1008"></a>
<span id="l1009">     * normal connection.</span><a href="#l1009"></a>
<span id="l1010">     *</span><a href="#l1010"></a>
<span id="l1011">     * Invoking this method preempts the system's default ProxySelector</span><a href="#l1011"></a>
<span id="l1012">     * settings.</span><a href="#l1012"></a>
<span id="l1013">     *</span><a href="#l1013"></a>
<span id="l1014">     * @param      proxy the Proxy through which this connection</span><a href="#l1014"></a>
<span id="l1015">     *             will be made. If direct connection is desired,</span><a href="#l1015"></a>
<span id="l1016">     *             Proxy.NO_PROXY should be specified.</span><a href="#l1016"></a>
<span id="l1017">     * @return     a {@code URLConnection} to the URL.</span><a href="#l1017"></a>
<span id="l1018">     * @exception  IOException  if an I/O exception occurs.</span><a href="#l1018"></a>
<span id="l1019">     * @exception  SecurityException if a security manager is present</span><a href="#l1019"></a>
<span id="l1020">     *             and the caller doesn't have permission to connect</span><a href="#l1020"></a>
<span id="l1021">     *             to the proxy.</span><a href="#l1021"></a>
<span id="l1022">     * @exception  IllegalArgumentException will be thrown if proxy is null,</span><a href="#l1022"></a>
<span id="l1023">     *             or proxy has the wrong type</span><a href="#l1023"></a>
<span id="l1024">     * @exception  UnsupportedOperationException if the subclass that</span><a href="#l1024"></a>
<span id="l1025">     *             implements the protocol handler doesn't support</span><a href="#l1025"></a>
<span id="l1026">     *             this method.</span><a href="#l1026"></a>
<span id="l1027">     * @see        java.net.URL#URL(java.lang.String, java.lang.String,</span><a href="#l1027"></a>
<span id="l1028">     *             int, java.lang.String)</span><a href="#l1028"></a>
<span id="l1029">     * @see        java.net.URLConnection</span><a href="#l1029"></a>
<span id="l1030">     * @see        java.net.URLStreamHandler#openConnection(java.net.URL,</span><a href="#l1030"></a>
<span id="l1031">     *             java.net.Proxy)</span><a href="#l1031"></a>
<span id="l1032">     * @since      1.5</span><a href="#l1032"></a>
<span id="l1033">     */</span><a href="#l1033"></a>
<span id="l1034">    public URLConnection openConnection(Proxy proxy)</span><a href="#l1034"></a>
<span id="l1035">        throws java.io.IOException {</span><a href="#l1035"></a>
<span id="l1036">        if (proxy == null) {</span><a href="#l1036"></a>
<span id="l1037">            throw new IllegalArgumentException(&quot;proxy can not be null&quot;);</span><a href="#l1037"></a>
<span id="l1038">        }</span><a href="#l1038"></a>
<span id="l1039"></span><a href="#l1039"></a>
<span id="l1040">        // Create a copy of Proxy as a security measure</span><a href="#l1040"></a>
<span id="l1041">        Proxy p = proxy == Proxy.NO_PROXY ? Proxy.NO_PROXY : sun.net.ApplicationProxy.create(proxy);</span><a href="#l1041"></a>
<span id="l1042">        SecurityManager sm = System.getSecurityManager();</span><a href="#l1042"></a>
<span id="l1043">        if (p.type() != Proxy.Type.DIRECT &amp;&amp; sm != null) {</span><a href="#l1043"></a>
<span id="l1044">            InetSocketAddress epoint = (InetSocketAddress) p.address();</span><a href="#l1044"></a>
<span id="l1045">            if (epoint.isUnresolved())</span><a href="#l1045"></a>
<span id="l1046">                sm.checkConnect(epoint.getHostName(), epoint.getPort());</span><a href="#l1046"></a>
<span id="l1047">            else</span><a href="#l1047"></a>
<span id="l1048">                sm.checkConnect(epoint.getAddress().getHostAddress(),</span><a href="#l1048"></a>
<span id="l1049">                                epoint.getPort());</span><a href="#l1049"></a>
<span id="l1050">        }</span><a href="#l1050"></a>
<span id="l1051">        return handler.openConnection(this, p);</span><a href="#l1051"></a>
<span id="l1052">    }</span><a href="#l1052"></a>
<span id="l1053"></span><a href="#l1053"></a>
<span id="l1054">    /**</span><a href="#l1054"></a>
<span id="l1055">     * Opens a connection to this {@code URL} and returns an</span><a href="#l1055"></a>
<span id="l1056">     * {@code InputStream} for reading from that connection. This</span><a href="#l1056"></a>
<span id="l1057">     * method is a shorthand for:</span><a href="#l1057"></a>
<span id="l1058">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1058"></a>
<span id="l1059">     *     openConnection().getInputStream()</span><a href="#l1059"></a>
<span id="l1060">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1060"></a>
<span id="l1061">     *</span><a href="#l1061"></a>
<span id="l1062">     * @return     an input stream for reading from the URL connection.</span><a href="#l1062"></a>
<span id="l1063">     * @exception  IOException  if an I/O exception occurs.</span><a href="#l1063"></a>
<span id="l1064">     * @see        java.net.URL#openConnection()</span><a href="#l1064"></a>
<span id="l1065">     * @see        java.net.URLConnection#getInputStream()</span><a href="#l1065"></a>
<span id="l1066">     */</span><a href="#l1066"></a>
<span id="l1067">    public final InputStream openStream() throws java.io.IOException {</span><a href="#l1067"></a>
<span id="l1068">        return openConnection().getInputStream();</span><a href="#l1068"></a>
<span id="l1069">    }</span><a href="#l1069"></a>
<span id="l1070"></span><a href="#l1070"></a>
<span id="l1071">    /**</span><a href="#l1071"></a>
<span id="l1072">     * Gets the contents of this URL. This method is a shorthand for:</span><a href="#l1072"></a>
<span id="l1073">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1073"></a>
<span id="l1074">     *     openConnection().getContent()</span><a href="#l1074"></a>
<span id="l1075">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1075"></a>
<span id="l1076">     *</span><a href="#l1076"></a>
<span id="l1077">     * @return     the contents of this URL.</span><a href="#l1077"></a>
<span id="l1078">     * @exception  IOException  if an I/O exception occurs.</span><a href="#l1078"></a>
<span id="l1079">     * @see        java.net.URLConnection#getContent()</span><a href="#l1079"></a>
<span id="l1080">     */</span><a href="#l1080"></a>
<span id="l1081">    public final Object getContent() throws java.io.IOException {</span><a href="#l1081"></a>
<span id="l1082">        return openConnection().getContent();</span><a href="#l1082"></a>
<span id="l1083">    }</span><a href="#l1083"></a>
<span id="l1084"></span><a href="#l1084"></a>
<span id="l1085">    /**</span><a href="#l1085"></a>
<span id="l1086">     * Gets the contents of this URL. This method is a shorthand for:</span><a href="#l1086"></a>
<span id="l1087">     * &lt;blockquote&gt;&lt;pre&gt;</span><a href="#l1087"></a>
<span id="l1088">     *     openConnection().getContent(Class[])</span><a href="#l1088"></a>
<span id="l1089">     * &lt;/pre&gt;&lt;/blockquote&gt;</span><a href="#l1089"></a>
<span id="l1090">     *</span><a href="#l1090"></a>
<span id="l1091">     * @param classes an array of Java types</span><a href="#l1091"></a>
<span id="l1092">     * @return     the content object of this URL that is the first match of</span><a href="#l1092"></a>
<span id="l1093">     *               the types specified in the classes array.</span><a href="#l1093"></a>
<span id="l1094">     *               null if none of the requested types are supported.</span><a href="#l1094"></a>
<span id="l1095">     * @exception  IOException  if an I/O exception occurs.</span><a href="#l1095"></a>
<span id="l1096">     * @see        java.net.URLConnection#getContent(Class[])</span><a href="#l1096"></a>
<span id="l1097">     * @since 1.3</span><a href="#l1097"></a>
<span id="l1098">     */</span><a href="#l1098"></a>
<span id="l1099">    public final Object getContent(Class[] classes)</span><a href="#l1099"></a>
<span id="l1100">    throws java.io.IOException {</span><a href="#l1100"></a>
<span id="l1101">        return openConnection().getContent(classes);</span><a href="#l1101"></a>
<span id="l1102">    }</span><a href="#l1102"></a>
<span id="l1103"></span><a href="#l1103"></a>
<span id="l1104">    /**</span><a href="#l1104"></a>
<span id="l1105">     * The URLStreamHandler factory.</span><a href="#l1105"></a>
<span id="l1106">     */</span><a href="#l1106"></a>
<span id="l1107">    static URLStreamHandlerFactory factory;</span><a href="#l1107"></a>
<span id="l1108"></span><a href="#l1108"></a>
<span id="l1109">    /**</span><a href="#l1109"></a>
<span id="l1110">     * Sets an application's {@code URLStreamHandlerFactory}.</span><a href="#l1110"></a>
<span id="l1111">     * This method can be called at most once in a given Java Virtual</span><a href="#l1111"></a>
<span id="l1112">     * Machine.</span><a href="#l1112"></a>
<span id="l1113">     *</span><a href="#l1113"></a>
<span id="l1114">     *&lt;p&gt; The {@code URLStreamHandlerFactory} instance is used to</span><a href="#l1114"></a>
<span id="l1115">     *construct a stream protocol handler from a protocol name.</span><a href="#l1115"></a>
<span id="l1116">     *</span><a href="#l1116"></a>
<span id="l1117">     * &lt;p&gt; If there is a security manager, this method first calls</span><a href="#l1117"></a>
<span id="l1118">     * the security manager's {@code checkSetFactory} method</span><a href="#l1118"></a>
<span id="l1119">     * to ensure the operation is allowed.</span><a href="#l1119"></a>
<span id="l1120">     * This could result in a SecurityException.</span><a href="#l1120"></a>
<span id="l1121">     *</span><a href="#l1121"></a>
<span id="l1122">     * @param      fac   the desired factory.</span><a href="#l1122"></a>
<span id="l1123">     * @exception  Error  if the application has already set a factory.</span><a href="#l1123"></a>
<span id="l1124">     * @exception  SecurityException  if a security manager exists and its</span><a href="#l1124"></a>
<span id="l1125">     *             {@code checkSetFactory} method doesn't allow</span><a href="#l1125"></a>
<span id="l1126">     *             the operation.</span><a href="#l1126"></a>
<span id="l1127">     * @see        java.net.URL#URL(java.lang.String, java.lang.String,</span><a href="#l1127"></a>
<span id="l1128">     *             int, java.lang.String)</span><a href="#l1128"></a>
<span id="l1129">     * @see        java.net.URLStreamHandlerFactory</span><a href="#l1129"></a>
<span id="l1130">     * @see        SecurityManager#checkSetFactory</span><a href="#l1130"></a>
<span id="l1131">     */</span><a href="#l1131"></a>
<span id="l1132">    public static void setURLStreamHandlerFactory(URLStreamHandlerFactory fac) {</span><a href="#l1132"></a>
<span id="l1133">        synchronized (streamHandlerLock) {</span><a href="#l1133"></a>
<span id="l1134">            if (factory != null) {</span><a href="#l1134"></a>
<span id="l1135">                throw new Error(&quot;factory already defined&quot;);</span><a href="#l1135"></a>
<span id="l1136">            }</span><a href="#l1136"></a>
<span id="l1137">            SecurityManager security = System.getSecurityManager();</span><a href="#l1137"></a>
<span id="l1138">            if (security != null) {</span><a href="#l1138"></a>
<span id="l1139">                security.checkSetFactory();</span><a href="#l1139"></a>
<span id="l1140">            }</span><a href="#l1140"></a>
<span id="l1141">            handlers.clear();</span><a href="#l1141"></a>
<span id="l1142">            factory = fac;</span><a href="#l1142"></a>
<span id="l1143">        }</span><a href="#l1143"></a>
<span id="l1144">    }</span><a href="#l1144"></a>
<span id="l1145"></span><a href="#l1145"></a>
<span id="l1146">    /**</span><a href="#l1146"></a>
<span id="l1147">     * A table of protocol handlers.</span><a href="#l1147"></a>
<span id="l1148">     */</span><a href="#l1148"></a>
<span id="l1149">    static Hashtable&lt;String,URLStreamHandler&gt; handlers = new Hashtable&lt;&gt;();</span><a href="#l1149"></a>
<span id="l1150">    private static Object streamHandlerLock = new Object();</span><a href="#l1150"></a>
<span id="l1151"></span><a href="#l1151"></a>
<span id="l1152">    /**</span><a href="#l1152"></a>
<span id="l1153">     * Returns the Stream Handler.</span><a href="#l1153"></a>
<span id="l1154">     * @param protocol the protocol to use</span><a href="#l1154"></a>
<span id="l1155">     */</span><a href="#l1155"></a>
<span id="l1156">    static URLStreamHandler getURLStreamHandler(String protocol) {</span><a href="#l1156"></a>
<span id="l1157"></span><a href="#l1157"></a>
<span id="l1158">        URLStreamHandler handler = handlers.get(protocol);</span><a href="#l1158"></a>
<span id="l1159">        if (handler == null) {</span><a href="#l1159"></a>
<span id="l1160"></span><a href="#l1160"></a>
<span id="l1161">            boolean checkedWithFactory = false;</span><a href="#l1161"></a>
<span id="l1162"></span><a href="#l1162"></a>
<span id="l1163">            // Use the factory (if any)</span><a href="#l1163"></a>
<span id="l1164">            if (factory != null) {</span><a href="#l1164"></a>
<span id="l1165">                handler = factory.createURLStreamHandler(protocol);</span><a href="#l1165"></a>
<span id="l1166">                checkedWithFactory = true;</span><a href="#l1166"></a>
<span id="l1167">            }</span><a href="#l1167"></a>
<span id="l1168"></span><a href="#l1168"></a>
<span id="l1169">            // Try java protocol handler</span><a href="#l1169"></a>
<span id="l1170">            if (handler == null) {</span><a href="#l1170"></a>
<span id="l1171">                String packagePrefixList = null;</span><a href="#l1171"></a>
<span id="l1172"></span><a href="#l1172"></a>
<span id="l1173">                packagePrefixList</span><a href="#l1173"></a>
<span id="l1174">                    = java.security.AccessController.doPrivileged(</span><a href="#l1174"></a>
<span id="l1175">                    new sun.security.action.GetPropertyAction(</span><a href="#l1175"></a>
<span id="l1176">                        protocolPathProp,&quot;&quot;));</span><a href="#l1176"></a>
<span id="l1177">                if (packagePrefixList != &quot;&quot;) {</span><a href="#l1177"></a>
<span id="l1178">                    packagePrefixList += &quot;|&quot;;</span><a href="#l1178"></a>
<span id="l1179">                }</span><a href="#l1179"></a>
<span id="l1180"></span><a href="#l1180"></a>
<span id="l1181">                // REMIND: decide whether to allow the &quot;null&quot; class prefix</span><a href="#l1181"></a>
<span id="l1182">                // or not.</span><a href="#l1182"></a>
<span id="l1183">                packagePrefixList += &quot;sun.net.www.protocol&quot;;</span><a href="#l1183"></a>
<span id="l1184"></span><a href="#l1184"></a>
<span id="l1185">                StringTokenizer packagePrefixIter =</span><a href="#l1185"></a>
<span id="l1186">                    new StringTokenizer(packagePrefixList, &quot;|&quot;);</span><a href="#l1186"></a>
<span id="l1187"></span><a href="#l1187"></a>
<span id="l1188">                while (handler == null &amp;&amp;</span><a href="#l1188"></a>
<span id="l1189">                       packagePrefixIter.hasMoreTokens()) {</span><a href="#l1189"></a>
<span id="l1190"></span><a href="#l1190"></a>
<span id="l1191">                    String packagePrefix =</span><a href="#l1191"></a>
<span id="l1192">                      packagePrefixIter.nextToken().trim();</span><a href="#l1192"></a>
<span id="l1193">                    try {</span><a href="#l1193"></a>
<span id="l1194">                        String clsName = packagePrefix + &quot;.&quot; + protocol +</span><a href="#l1194"></a>
<span id="l1195">                          &quot;.Handler&quot;;</span><a href="#l1195"></a>
<span id="l1196">                        Class&lt;?&gt; cls = null;</span><a href="#l1196"></a>
<span id="l1197">                        try {</span><a href="#l1197"></a>
<span id="l1198">                            cls = Class.forName(clsName);</span><a href="#l1198"></a>
<span id="l1199">                        } catch (ClassNotFoundException e) {</span><a href="#l1199"></a>
<span id="l1200">                            ClassLoader cl = ClassLoader.getSystemClassLoader();</span><a href="#l1200"></a>
<span id="l1201">                            if (cl != null) {</span><a href="#l1201"></a>
<span id="l1202">                                cls = cl.loadClass(clsName);</span><a href="#l1202"></a>
<span id="l1203">                            }</span><a href="#l1203"></a>
<span id="l1204">                        }</span><a href="#l1204"></a>
<span id="l1205">                        if (cls != null) {</span><a href="#l1205"></a>
<span id="l1206">                            handler  =</span><a href="#l1206"></a>
<span id="l1207">                              (URLStreamHandler)cls.newInstance();</span><a href="#l1207"></a>
<span id="l1208">                        }</span><a href="#l1208"></a>
<span id="l1209">                    } catch (Exception e) {</span><a href="#l1209"></a>
<span id="l1210">                        // any number of exceptions can get thrown here</span><a href="#l1210"></a>
<span id="l1211">                    }</span><a href="#l1211"></a>
<span id="l1212">                }</span><a href="#l1212"></a>
<span id="l1213">            }</span><a href="#l1213"></a>
<span id="l1214"></span><a href="#l1214"></a>
<span id="l1215">            synchronized (streamHandlerLock) {</span><a href="#l1215"></a>
<span id="l1216"></span><a href="#l1216"></a>
<span id="l1217">                URLStreamHandler handler2 = null;</span><a href="#l1217"></a>
<span id="l1218"></span><a href="#l1218"></a>
<span id="l1219">                // Check again with hashtable just in case another</span><a href="#l1219"></a>
<span id="l1220">                // thread created a handler since we last checked</span><a href="#l1220"></a>
<span id="l1221">                handler2 = handlers.get(protocol);</span><a href="#l1221"></a>
<span id="l1222"></span><a href="#l1222"></a>
<span id="l1223">                if (handler2 != null) {</span><a href="#l1223"></a>
<span id="l1224">                    return handler2;</span><a href="#l1224"></a>
<span id="l1225">                }</span><a href="#l1225"></a>
<span id="l1226"></span><a href="#l1226"></a>
<span id="l1227">                // Check with factory if another thread set a</span><a href="#l1227"></a>
<span id="l1228">                // factory since our last check</span><a href="#l1228"></a>
<span id="l1229">                if (!checkedWithFactory &amp;&amp; factory != null) {</span><a href="#l1229"></a>
<span id="l1230">                    handler2 = factory.createURLStreamHandler(protocol);</span><a href="#l1230"></a>
<span id="l1231">                }</span><a href="#l1231"></a>
<span id="l1232"></span><a href="#l1232"></a>
<span id="l1233">                if (handler2 != null) {</span><a href="#l1233"></a>
<span id="l1234">                    // The handler from the factory must be given more</span><a href="#l1234"></a>
<span id="l1235">                    // importance. Discard the default handler that</span><a href="#l1235"></a>
<span id="l1236">                    // this thread created.</span><a href="#l1236"></a>
<span id="l1237">                    handler = handler2;</span><a href="#l1237"></a>
<span id="l1238">                }</span><a href="#l1238"></a>
<span id="l1239"></span><a href="#l1239"></a>
<span id="l1240">                // Insert this handler into the hashtable</span><a href="#l1240"></a>
<span id="l1241">                if (handler != null) {</span><a href="#l1241"></a>
<span id="l1242">                    handlers.put(protocol, handler);</span><a href="#l1242"></a>
<span id="l1243">                }</span><a href="#l1243"></a>
<span id="l1244"></span><a href="#l1244"></a>
<span id="l1245">            }</span><a href="#l1245"></a>
<span id="l1246">        }</span><a href="#l1246"></a>
<span id="l1247"></span><a href="#l1247"></a>
<span id="l1248">        return handler;</span><a href="#l1248"></a>
<span id="l1249"></span><a href="#l1249"></a>
<span id="l1250">    }</span><a href="#l1250"></a>
<span id="l1251"></span><a href="#l1251"></a>
<span id="l1252">    /**</span><a href="#l1252"></a>
<span id="l1253">     * @serialField    protocol String</span><a href="#l1253"></a>
<span id="l1254">     *</span><a href="#l1254"></a>
<span id="l1255">     * @serialField    host String</span><a href="#l1255"></a>
<span id="l1256">     *</span><a href="#l1256"></a>
<span id="l1257">     * @serialField    port int</span><a href="#l1257"></a>
<span id="l1258">     *</span><a href="#l1258"></a>
<span id="l1259">     * @serialField    authority String</span><a href="#l1259"></a>
<span id="l1260">     *</span><a href="#l1260"></a>
<span id="l1261">     * @serialField    file String</span><a href="#l1261"></a>
<span id="l1262">     *</span><a href="#l1262"></a>
<span id="l1263">     * @serialField    ref String</span><a href="#l1263"></a>
<span id="l1264">     *</span><a href="#l1264"></a>
<span id="l1265">     * @serialField    hashCode int</span><a href="#l1265"></a>
<span id="l1266">     *</span><a href="#l1266"></a>
<span id="l1267">     */</span><a href="#l1267"></a>
<span id="l1268">    private static final ObjectStreamField[] serialPersistentFields = {</span><a href="#l1268"></a>
<span id="l1269">        new ObjectStreamField(&quot;protocol&quot;, String.class),</span><a href="#l1269"></a>
<span id="l1270">        new ObjectStreamField(&quot;host&quot;, String.class),</span><a href="#l1270"></a>
<span id="l1271">        new ObjectStreamField(&quot;port&quot;, int.class),</span><a href="#l1271"></a>
<span id="l1272">        new ObjectStreamField(&quot;authority&quot;, String.class),</span><a href="#l1272"></a>
<span id="l1273">        new ObjectStreamField(&quot;file&quot;, String.class),</span><a href="#l1273"></a>
<span id="l1274">        new ObjectStreamField(&quot;ref&quot;, String.class),</span><a href="#l1274"></a>
<span id="l1275">        new ObjectStreamField(&quot;hashCode&quot;, int.class), };</span><a href="#l1275"></a>
<span id="l1276"></span><a href="#l1276"></a>
<span id="l1277">    /**</span><a href="#l1277"></a>
<span id="l1278">     * WriteObject is called to save the state of the URL to an</span><a href="#l1278"></a>
<span id="l1279">     * ObjectOutputStream. The handler is not saved since it is</span><a href="#l1279"></a>
<span id="l1280">     * specific to this system.</span><a href="#l1280"></a>
<span id="l1281">     *</span><a href="#l1281"></a>
<span id="l1282">     * @serialData the default write object value. When read back in,</span><a href="#l1282"></a>
<span id="l1283">     * the reader must ensure that calling getURLStreamHandler with</span><a href="#l1283"></a>
<span id="l1284">     * the protocol variable returns a valid URLStreamHandler and</span><a href="#l1284"></a>
<span id="l1285">     * throw an IOException if it does not.</span><a href="#l1285"></a>
<span id="l1286">     */</span><a href="#l1286"></a>
<span id="l1287">    private synchronized void writeObject(java.io.ObjectOutputStream s)</span><a href="#l1287"></a>
<span id="l1288">        throws IOException</span><a href="#l1288"></a>
<span id="l1289">    {</span><a href="#l1289"></a>
<span id="l1290">        s.defaultWriteObject(); // write the fields</span><a href="#l1290"></a>
<span id="l1291">    }</span><a href="#l1291"></a>
<span id="l1292"></span><a href="#l1292"></a>
<span id="l1293">    /**</span><a href="#l1293"></a>
<span id="l1294">     * readObject is called to restore the state of the URL from the</span><a href="#l1294"></a>
<span id="l1295">     * stream.  It reads the components of the URL and finds the local</span><a href="#l1295"></a>
<span id="l1296">     * stream handler.</span><a href="#l1296"></a>
<span id="l1297">     */</span><a href="#l1297"></a>
<span id="l1298">    private synchronized void readObject(java.io.ObjectInputStream s)</span><a href="#l1298"></a>
<span id="l1299">            throws IOException, ClassNotFoundException {</span><a href="#l1299"></a>
<span id="l1300">        GetField gf = s.readFields();</span><a href="#l1300"></a>
<span id="l1301">        String protocol = (String)gf.get(&quot;protocol&quot;, null);</span><a href="#l1301"></a>
<span id="l1302">        if (getURLStreamHandler(protocol) == null) {</span><a href="#l1302"></a>
<span id="l1303">            throw new IOException(&quot;unknown protocol: &quot; + protocol);</span><a href="#l1303"></a>
<span id="l1304">        }</span><a href="#l1304"></a>
<span id="l1305">        String host = (String)gf.get(&quot;host&quot;, null);</span><a href="#l1305"></a>
<span id="l1306">        int port = gf.get(&quot;port&quot;, -1);</span><a href="#l1306"></a>
<span id="l1307">        String authority = (String)gf.get(&quot;authority&quot;, null);</span><a href="#l1307"></a>
<span id="l1308">        String file = (String)gf.get(&quot;file&quot;, null);</span><a href="#l1308"></a>
<span id="l1309">        String ref = (String)gf.get(&quot;ref&quot;, null);</span><a href="#l1309"></a>
<span id="l1310">        int hashCode = gf.get(&quot;hashCode&quot;, -1);</span><a href="#l1310"></a>
<span id="l1311">        if (authority == null</span><a href="#l1311"></a>
<span id="l1312">                &amp;&amp; ((host != null &amp;&amp; host.length() &gt; 0) || port != -1)) {</span><a href="#l1312"></a>
<span id="l1313">            if (host == null)</span><a href="#l1313"></a>
<span id="l1314">                host = &quot;&quot;;</span><a href="#l1314"></a>
<span id="l1315">            authority = (port == -1) ? host : host + &quot;:&quot; + port;</span><a href="#l1315"></a>
<span id="l1316">        }</span><a href="#l1316"></a>
<span id="l1317">        tempState = new UrlDeserializedState(protocol, host, port, authority,</span><a href="#l1317"></a>
<span id="l1318">               file, ref, hashCode);</span><a href="#l1318"></a>
<span id="l1319">    }</span><a href="#l1319"></a>
<span id="l1320"></span><a href="#l1320"></a>
<span id="l1321">    /**</span><a href="#l1321"></a>
<span id="l1322">     * Replaces the de-serialized object with an URL object.</span><a href="#l1322"></a>
<span id="l1323">     *</span><a href="#l1323"></a>
<span id="l1324">     * @return a newly created object from the deserialzed state.</span><a href="#l1324"></a>
<span id="l1325">     *</span><a href="#l1325"></a>
<span id="l1326">     * @throws ObjectStreamException if a new object replacing this</span><a href="#l1326"></a>
<span id="l1327">     * object could not be created</span><a href="#l1327"></a>
<span id="l1328">     */</span><a href="#l1328"></a>
<span id="l1329"></span><a href="#l1329"></a>
<span id="l1330">   private Object readResolve() throws ObjectStreamException {</span><a href="#l1330"></a>
<span id="l1331"></span><a href="#l1331"></a>
<span id="l1332">        URLStreamHandler handler = null;</span><a href="#l1332"></a>
<span id="l1333">        // already been checked in readObject</span><a href="#l1333"></a>
<span id="l1334">        handler = getURLStreamHandler(tempState.getProtocol());</span><a href="#l1334"></a>
<span id="l1335"></span><a href="#l1335"></a>
<span id="l1336">        URL replacementURL = null;</span><a href="#l1336"></a>
<span id="l1337">        if (isBuiltinStreamHandler(handler.getClass().getName())) {</span><a href="#l1337"></a>
<span id="l1338">            replacementURL = fabricateNewURL();</span><a href="#l1338"></a>
<span id="l1339">        } else {</span><a href="#l1339"></a>
<span id="l1340">            replacementURL = setDeserializedFields(handler);</span><a href="#l1340"></a>
<span id="l1341">        }</span><a href="#l1341"></a>
<span id="l1342">        return replacementURL;</span><a href="#l1342"></a>
<span id="l1343">    }</span><a href="#l1343"></a>
<span id="l1344"></span><a href="#l1344"></a>
<span id="l1345">    private URL setDeserializedFields(URLStreamHandler handler) {</span><a href="#l1345"></a>
<span id="l1346">        URL replacementURL;</span><a href="#l1346"></a>
<span id="l1347">        String userInfo = null;</span><a href="#l1347"></a>
<span id="l1348">        String protocol = tempState.getProtocol();</span><a href="#l1348"></a>
<span id="l1349">        String host = tempState.getHost();</span><a href="#l1349"></a>
<span id="l1350">        int port = tempState.getPort();</span><a href="#l1350"></a>
<span id="l1351">        String authority = tempState.getAuthority();</span><a href="#l1351"></a>
<span id="l1352">        String file = tempState.getFile();</span><a href="#l1352"></a>
<span id="l1353">        String ref = tempState.getRef();</span><a href="#l1353"></a>
<span id="l1354">        int hashCode = tempState.getHashCode();</span><a href="#l1354"></a>
<span id="l1355"></span><a href="#l1355"></a>
<span id="l1356"></span><a href="#l1356"></a>
<span id="l1357">        // Construct authority part</span><a href="#l1357"></a>
<span id="l1358">        if (authority == null</span><a href="#l1358"></a>
<span id="l1359">            &amp;&amp; ((host != null &amp;&amp; host.length() &gt; 0) || port != -1)) {</span><a href="#l1359"></a>
<span id="l1360">            if (host == null)</span><a href="#l1360"></a>
<span id="l1361">                host = &quot;&quot;;</span><a href="#l1361"></a>
<span id="l1362">            authority = (port == -1) ? host : host + &quot;:&quot; + port;</span><a href="#l1362"></a>
<span id="l1363"></span><a href="#l1363"></a>
<span id="l1364">            // Handle hosts with userInfo in them</span><a href="#l1364"></a>
<span id="l1365">            int at = host.lastIndexOf('@');</span><a href="#l1365"></a>
<span id="l1366">            if (at != -1) {</span><a href="#l1366"></a>
<span id="l1367">                userInfo = host.substring(0, at);</span><a href="#l1367"></a>
<span id="l1368">                host = host.substring(at+1);</span><a href="#l1368"></a>
<span id="l1369">            }</span><a href="#l1369"></a>
<span id="l1370">        } else if (authority != null) {</span><a href="#l1370"></a>
<span id="l1371">            // Construct user info part</span><a href="#l1371"></a>
<span id="l1372">            int ind = authority.indexOf('@');</span><a href="#l1372"></a>
<span id="l1373">            if (ind != -1)</span><a href="#l1373"></a>
<span id="l1374">                userInfo = authority.substring(0, ind);</span><a href="#l1374"></a>
<span id="l1375">        }</span><a href="#l1375"></a>
<span id="l1376"></span><a href="#l1376"></a>
<span id="l1377">        // Construct path and query part</span><a href="#l1377"></a>
<span id="l1378">        String path = null;</span><a href="#l1378"></a>
<span id="l1379">        String query = null;</span><a href="#l1379"></a>
<span id="l1380">        if (file != null) {</span><a href="#l1380"></a>
<span id="l1381">            // Fix: only do this if hierarchical?</span><a href="#l1381"></a>
<span id="l1382">            int q = file.lastIndexOf('?');</span><a href="#l1382"></a>
<span id="l1383">            if (q != -1) {</span><a href="#l1383"></a>
<span id="l1384">                query = file.substring(q+1);</span><a href="#l1384"></a>
<span id="l1385">                path = file.substring(0, q);</span><a href="#l1385"></a>
<span id="l1386">            } else</span><a href="#l1386"></a>
<span id="l1387">                path = file;</span><a href="#l1387"></a>
<span id="l1388">        }</span><a href="#l1388"></a>
<span id="l1389"></span><a href="#l1389"></a>
<span id="l1390">        // Set the object fields.</span><a href="#l1390"></a>
<span id="l1391">        this.protocol = protocol;</span><a href="#l1391"></a>
<span id="l1392">        this.host = host;</span><a href="#l1392"></a>
<span id="l1393">        this.port = port;</span><a href="#l1393"></a>
<span id="l1394">        this.file = file;</span><a href="#l1394"></a>
<span id="l1395">        this.authority = authority;</span><a href="#l1395"></a>
<span id="l1396">        this.ref = ref;</span><a href="#l1396"></a>
<span id="l1397">        this.hashCode = hashCode;</span><a href="#l1397"></a>
<span id="l1398">        this.handler = handler;</span><a href="#l1398"></a>
<span id="l1399">        this.query = query;</span><a href="#l1399"></a>
<span id="l1400">        this.path = path;</span><a href="#l1400"></a>
<span id="l1401">        this.userInfo = userInfo;</span><a href="#l1401"></a>
<span id="l1402">        replacementURL = this;</span><a href="#l1402"></a>
<span id="l1403">        return replacementURL;</span><a href="#l1403"></a>
<span id="l1404">    }</span><a href="#l1404"></a>
<span id="l1405"></span><a href="#l1405"></a>
<span id="l1406">    private URL fabricateNewURL()</span><a href="#l1406"></a>
<span id="l1407">                throws InvalidObjectException {</span><a href="#l1407"></a>
<span id="l1408">        // create URL string from deserialized object</span><a href="#l1408"></a>
<span id="l1409">        URL replacementURL = null;</span><a href="#l1409"></a>
<span id="l1410">        String urlString = tempState.reconstituteUrlString();</span><a href="#l1410"></a>
<span id="l1411"></span><a href="#l1411"></a>
<span id="l1412">        try {</span><a href="#l1412"></a>
<span id="l1413">            replacementURL = new URL(urlString);</span><a href="#l1413"></a>
<span id="l1414">        } catch (MalformedURLException mEx) {</span><a href="#l1414"></a>
<span id="l1415">            resetState();</span><a href="#l1415"></a>
<span id="l1416">            InvalidObjectException invoEx = new InvalidObjectException(</span><a href="#l1416"></a>
<span id="l1417">                    &quot;Malformed URL: &quot; + urlString);</span><a href="#l1417"></a>
<span id="l1418">            invoEx.initCause(mEx);</span><a href="#l1418"></a>
<span id="l1419">            throw invoEx;</span><a href="#l1419"></a>
<span id="l1420">        }</span><a href="#l1420"></a>
<span id="l1421">        replacementURL.setSerializedHashCode(tempState.getHashCode());</span><a href="#l1421"></a>
<span id="l1422">        resetState();</span><a href="#l1422"></a>
<span id="l1423">        return replacementURL;</span><a href="#l1423"></a>
<span id="l1424">    }</span><a href="#l1424"></a>
<span id="l1425"></span><a href="#l1425"></a>
<span id="l1426">    boolean isBuiltinStreamHandler(URLStreamHandler handler) {</span><a href="#l1426"></a>
<span id="l1427">        Class&lt;?&gt; handlerClass = handler.getClass();</span><a href="#l1427"></a>
<span id="l1428">        return isBuiltinStreamHandler(handlerClass.getName())</span><a href="#l1428"></a>
<span id="l1429">                || VM.isSystemDomainLoader(handlerClass.getClassLoader());</span><a href="#l1429"></a>
<span id="l1430">    }</span><a href="#l1430"></a>
<span id="l1431"></span><a href="#l1431"></a>
<span id="l1432">    private boolean isBuiltinStreamHandler(String handlerClassName) {</span><a href="#l1432"></a>
<span id="l1433">        return (handlerClassName.startsWith(BUILTIN_HANDLERS_PREFIX));</span><a href="#l1433"></a>
<span id="l1434">    }</span><a href="#l1434"></a>
<span id="l1435"></span><a href="#l1435"></a>
<span id="l1436">    private void resetState() {</span><a href="#l1436"></a>
<span id="l1437">        this.protocol = null;</span><a href="#l1437"></a>
<span id="l1438">        this.host = null;</span><a href="#l1438"></a>
<span id="l1439">        this.port = -1;</span><a href="#l1439"></a>
<span id="l1440">        this.file = null;</span><a href="#l1440"></a>
<span id="l1441">        this.authority = null;</span><a href="#l1441"></a>
<span id="l1442">        this.ref = null;</span><a href="#l1442"></a>
<span id="l1443">        this.hashCode = -1;</span><a href="#l1443"></a>
<span id="l1444">        this.handler = null;</span><a href="#l1444"></a>
<span id="l1445">        this.query = null;</span><a href="#l1445"></a>
<span id="l1446">        this.path = null;</span><a href="#l1446"></a>
<span id="l1447">        this.userInfo = null;</span><a href="#l1447"></a>
<span id="l1448">        this.tempState = null;</span><a href="#l1448"></a>
<span id="l1449">    }</span><a href="#l1449"></a>
<span id="l1450"></span><a href="#l1450"></a>
<span id="l1451">    private void setSerializedHashCode(int hc) {</span><a href="#l1451"></a>
<span id="l1452">        this.hashCode = hc;</span><a href="#l1452"></a>
<span id="l1453">    }</span><a href="#l1453"></a>
<span id="l1454">}</span><a href="#l1454"></a>
<span id="l1455"></span><a href="#l1455"></a>
<span id="l1456">class Parts {</span><a href="#l1456"></a>
<span id="l1457">    String path, query, ref;</span><a href="#l1457"></a>
<span id="l1458"></span><a href="#l1458"></a>
<span id="l1459">    Parts(String file) {</span><a href="#l1459"></a>
<span id="l1460">        int ind = file.indexOf('#');</span><a href="#l1460"></a>
<span id="l1461">        ref = ind &lt; 0 ? null: file.substring(ind + 1);</span><a href="#l1461"></a>
<span id="l1462">        file = ind &lt; 0 ? file: file.substring(0, ind);</span><a href="#l1462"></a>
<span id="l1463">        int q = file.lastIndexOf('?');</span><a href="#l1463"></a>
<span id="l1464">        if (q != -1) {</span><a href="#l1464"></a>
<span id="l1465">            query = file.substring(q+1);</span><a href="#l1465"></a>
<span id="l1466">            path = file.substring(0, q);</span><a href="#l1466"></a>
<span id="l1467">        } else {</span><a href="#l1467"></a>
<span id="l1468">            path = file;</span><a href="#l1468"></a>
<span id="l1469">        }</span><a href="#l1469"></a>
<span id="l1470">    }</span><a href="#l1470"></a>
<span id="l1471"></span><a href="#l1471"></a>
<span id="l1472">    String getPath() {</span><a href="#l1472"></a>
<span id="l1473">        return path;</span><a href="#l1473"></a>
<span id="l1474">    }</span><a href="#l1474"></a>
<span id="l1475"></span><a href="#l1475"></a>
<span id="l1476">    String getQuery() {</span><a href="#l1476"></a>
<span id="l1477">        return query;</span><a href="#l1477"></a>
<span id="l1478">    }</span><a href="#l1478"></a>
<span id="l1479"></span><a href="#l1479"></a>
<span id="l1480">    String getRef() {</span><a href="#l1480"></a>
<span id="l1481">        return ref;</span><a href="#l1481"></a>
<span id="l1482">    }</span><a href="#l1482"></a>
<span id="l1483">}</span><a href="#l1483"></a>
<span id="l1484"></span><a href="#l1484"></a>
<span id="l1485">final class UrlDeserializedState {</span><a href="#l1485"></a>
<span id="l1486">    private final String protocol;</span><a href="#l1486"></a>
<span id="l1487">    private final String host;</span><a href="#l1487"></a>
<span id="l1488">    private final int port;</span><a href="#l1488"></a>
<span id="l1489">    private final String authority;</span><a href="#l1489"></a>
<span id="l1490">    private final String file;</span><a href="#l1490"></a>
<span id="l1491">    private final String ref;</span><a href="#l1491"></a>
<span id="l1492">    private final int hashCode;</span><a href="#l1492"></a>
<span id="l1493"></span><a href="#l1493"></a>
<span id="l1494">    public UrlDeserializedState(String protocol,</span><a href="#l1494"></a>
<span id="l1495">                                String host, int port,</span><a href="#l1495"></a>
<span id="l1496">                                String authority, String file,</span><a href="#l1496"></a>
<span id="l1497">                                String ref, int hashCode) {</span><a href="#l1497"></a>
<span id="l1498">        this.protocol = protocol;</span><a href="#l1498"></a>
<span id="l1499">        this.host = host;</span><a href="#l1499"></a>
<span id="l1500">        this.port = port;</span><a href="#l1500"></a>
<span id="l1501">        this.authority = authority;</span><a href="#l1501"></a>
<span id="l1502">        this.file = file;</span><a href="#l1502"></a>
<span id="l1503">        this.ref = ref;</span><a href="#l1503"></a>
<span id="l1504">        this.hashCode = hashCode;</span><a href="#l1504"></a>
<span id="l1505">    }</span><a href="#l1505"></a>
<span id="l1506"></span><a href="#l1506"></a>
<span id="l1507">    String getProtocol() {</span><a href="#l1507"></a>
<span id="l1508">        return protocol;</span><a href="#l1508"></a>
<span id="l1509">    }</span><a href="#l1509"></a>
<span id="l1510"></span><a href="#l1510"></a>
<span id="l1511">    String getHost() {</span><a href="#l1511"></a>
<span id="l1512">        return host;</span><a href="#l1512"></a>
<span id="l1513">    }</span><a href="#l1513"></a>
<span id="l1514"></span><a href="#l1514"></a>
<span id="l1515">    String getAuthority () {</span><a href="#l1515"></a>
<span id="l1516">        return authority;</span><a href="#l1516"></a>
<span id="l1517">    }</span><a href="#l1517"></a>
<span id="l1518"></span><a href="#l1518"></a>
<span id="l1519">    int getPort() {</span><a href="#l1519"></a>
<span id="l1520">        return port;</span><a href="#l1520"></a>
<span id="l1521">    }</span><a href="#l1521"></a>
<span id="l1522"></span><a href="#l1522"></a>
<span id="l1523">    String getFile () {</span><a href="#l1523"></a>
<span id="l1524">        return file;</span><a href="#l1524"></a>
<span id="l1525">    }</span><a href="#l1525"></a>
<span id="l1526"></span><a href="#l1526"></a>
<span id="l1527">    String getRef () {</span><a href="#l1527"></a>
<span id="l1528">        return ref;</span><a href="#l1528"></a>
<span id="l1529">    }</span><a href="#l1529"></a>
<span id="l1530"></span><a href="#l1530"></a>
<span id="l1531">    int getHashCode () {</span><a href="#l1531"></a>
<span id="l1532">        return hashCode;</span><a href="#l1532"></a>
<span id="l1533">    }</span><a href="#l1533"></a>
<span id="l1534"></span><a href="#l1534"></a>
<span id="l1535">    String reconstituteUrlString() {</span><a href="#l1535"></a>
<span id="l1536"></span><a href="#l1536"></a>
<span id="l1537">        // pre-compute length of StringBuilder</span><a href="#l1537"></a>
<span id="l1538">        int len = protocol.length() + 1;</span><a href="#l1538"></a>
<span id="l1539">        if (authority != null &amp;&amp; authority.length() &gt; 0)</span><a href="#l1539"></a>
<span id="l1540">            len += 2 + authority.length();</span><a href="#l1540"></a>
<span id="l1541">        if (file != null) {</span><a href="#l1541"></a>
<span id="l1542">            len += file.length();</span><a href="#l1542"></a>
<span id="l1543">        }</span><a href="#l1543"></a>
<span id="l1544">        if (ref != null)</span><a href="#l1544"></a>
<span id="l1545">            len += 1 + ref.length();</span><a href="#l1545"></a>
<span id="l1546">        StringBuilder result = new StringBuilder(len);</span><a href="#l1546"></a>
<span id="l1547">        result.append(protocol);</span><a href="#l1547"></a>
<span id="l1548">        result.append(&quot;:&quot;);</span><a href="#l1548"></a>
<span id="l1549">        if (authority != null &amp;&amp; authority.length() &gt; 0) {</span><a href="#l1549"></a>
<span id="l1550">            result.append(&quot;//&quot;);</span><a href="#l1550"></a>
<span id="l1551">            result.append(authority);</span><a href="#l1551"></a>
<span id="l1552">        }</span><a href="#l1552"></a>
<span id="l1553">        if (file != null) {</span><a href="#l1553"></a>
<span id="l1554">            result.append(file);</span><a href="#l1554"></a>
<span id="l1555">        }</span><a href="#l1555"></a>
<span id="l1556">        if (ref != null) {</span><a href="#l1556"></a>
<span id="l1557">            result.append(&quot;#&quot;);</span><a href="#l1557"></a>
<span id="l1558">            result.append(ref);</span><a href="#l1558"></a>
<span id="l1559">        }</span><a href="#l1559"></a>
<span id="l1560">        return result.toString();</span><a href="#l1560"></a>
<span id="l1561">    }</span><a href="#l1561"></a>
<span id="l1562">}</span><a href="#l1562"></a></pre>
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

