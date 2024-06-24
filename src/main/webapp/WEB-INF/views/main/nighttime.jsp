<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>

<head>
  <title>Home</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body>

  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script type="text/javascript" src='<c:url value="/resources/js/locationInfo.js"/>'></script>

  <!--
// v0 by Vercel.
// https://v0.dev/t/xj2Pnfc88VG
-->
  <header class="w-full flex bg-white py-4 px-6 shadow-lg">
    <div class="flex-grow items-center justify-center ">
      <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
        class="bi bi-palette-fill" viewBox="0 0 16 16">
        <path
          d="M12.433 10.07C14.133 10.585 16 11.15 16 8a8 8 0 1 0-8 8c1.996 0 1.826-1.504 1.649-3.08-.124-1.101-.252-2.237.351-2.92.465-.527 1.42-.237 2.433.07zM8 5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm4.5 3a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zM5 6.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm.5 6.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
      </svg>
    </div>
    <nav class="flex space-x-4">
      <a href="/aimage" class="text-black hover:text-gray-700 transition-colors duration-200">Home</a>
    </nav>
  </header>

  <main
    class="h-screen w-full flex items-center justify-center bg-gradient-to-r from-violet-800 via-cyan-700 to-blue-950">
    <button onclick="location.href='/aimage/chat?isDayTime=false'"
      class="inline-flex items-center justify-center w-60 h-30 text-sm font-semibold ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-black bg-emerald-400 rounded-lg px-8 py-3 hover:bg-emerald-500 transition-colors duration-200 ease-in-out">
      그림 좀 그려 줄래?
    </button>
  </main>

</body>

</html>