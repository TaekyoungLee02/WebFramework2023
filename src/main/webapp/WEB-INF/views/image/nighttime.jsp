<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.5.0/kakao.min.js"
        integrity="sha384-kYPsUbBPlktXsY6/oNHSUDZoTX6+YI51f63jCPEIPFP09ttByAdxd2mEjKuhdqn4"
        crossorigin="anonymous"></script>
    <script type="text/javascript" src='<c:url value="/resources/js/shareKakao.js"/>'></script>
</head>

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

<main class="h-screen w-full flex items-center justify-center bg-gradient-to-r from-violet-800 via-cyan-700 to-blue-950">
    <div class="aspect-square mt-10 mb-10 h=fit">
        <div class="grid grid-cols-2 gap-4">
            <div class="relative group" id="image_1">

                <img alt="Image 1"
                    class="object-cover w-full h-full rounded-lg transition-all duration-200 ease-in-out group-hover:opacity-70"
                    width="300" height="300" style="aspect-ratio: 300 / 300; object-fit: cover;"
                    id="imgBody1" />
                <div
                    class="inline-flex space-x-10 absolute inset-0 bg-black opacity-0 group-hover:opacity-50 transition-opacity duration-200 ease-in-out rounded-lg flex items-center justify-center">
                    <!-- <button
                        class="inline-flex bg-white items-center justify-center rounded-md ring-offset-background focus-visible:outline-none disabled:pointer-events-none disabled:opacity-50 bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10 opacity-0 group-hover:opacity-100 transition-all duration-300">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-instagram"
                            viewBox="0 0 16 16">
                            <path
                                d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z" />
                        </svg>
                    </button> -->
                    <button onClick="shareKakao(0)"
                        class="inline-flex bg-white items-center justify-center rounded-md ring-offset-background focus-visible:outline-none disabled:pointer-events-none disabled:opacity-50 bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10 opacity-0 group-hover:opacity-100 transition-all duration-300">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
                            class="bi bi-chat-fill" viewBox="0 0 16 16">
                            <path
                                d="M8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6-.097 1.016-.417 2.13-.771 2.966-.079.186.074.394.273.362 2.256-.37 3.597-.938 4.18-1.234A9.06 9.06 0 0 0 8 15z" />
                        </svg>
                    </button>
                </div>
            </div>
            <div class="relative group" id="image_2">
                <img alt="Image 2"
                    class="object-cover w-full h-full rounded-lg transition-all duration-200 ease-in-out group-hover:opacity-70"
                    width="300" height="300" style="aspect-ratio: 300 / 300; object-fit: cover;"
                    id="imgBody2" />
                <div
                    class="inline-flex space-x-10 absolute inset-0 bg-black opacity-0 group-hover:opacity-50 transition-opacity duration-200 ease-in-out rounded-lg flex items-center justify-center">
                    <button onClick="shareKakao(1)"
                        class="inline-flex bg-white items-center justify-center rounded-md ring-offset-background focus-visible:outline-none disabled:pointer-events-none disabled:opacity-50 bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10 opacity-0 group-hover:opacity-100 transition-all duration-300">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
                            class="bi bi-chat-fill" viewBox="0 0 16 16">
                            <path
                                d="M8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6-.097 1.016-.417 2.13-.771 2.966-.079.186.074.394.273.362 2.256-.37 3.597-.938 4.18-1.234A9.06 9.06 0 0 0 8 15z" />
                        </svg>
                    </button>
                </div>
            </div>
            <div class="relative group" id="image_3">
                <img alt="Image 3"
                    class="object-cover w-full h-full rounded-lg transition-all duration-200 ease-in-out group-hover:opacity-70"
                    width="300" height="300" style="aspect-ratio: 300 / 300; object-fit: cover;"
                    id="imgBody3" />
                <div
                    class="inline-flex space-x-10 absolute inset-0 bg-black opacity-0 group-hover:opacity-50 transition-opacity duration-200 ease-in-out rounded-lg flex items-center justify-center">
                    <button onClick="shareKakao(2)"
                        class="inline-flex bg-white items-center justify-center rounded-md ring-offset-background focus-visible:outline-none disabled:pointer-events-none disabled:opacity-50 bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10 opacity-0 group-hover:opacity-100 transition-all duration-300">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
                            class="bi bi-chat-fill" viewBox="0 0 16 16">
                            <path
                                d="M8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6-.097 1.016-.417 2.13-.771 2.966-.079.186.074.394.273.362 2.256-.37 3.597-.938 4.18-1.234A9.06 9.06 0 0 0 8 15z" />
                        </svg>
                    </button>
                </div>
            </div>
            <div class="relative group" id="image_4">
                <img alt="Image 4"
                    class="object-cover w-full h-full rounded-lg transition-all duration-200 ease-in-out group-hover:opacity-70"
                    width="300" height="300" style="aspect-ratio: 300 / 300; object-fit: cover;"
                    id="imgBody4" />
                <div
                    class="inline-flex space-x-10 absolute inset-0 bg-black opacity-0 group-hover:opacity-50 transition-opacity duration-200 ease-in-out rounded-lg flex items-center justify-center">
                    <button onClick="shareKakao(3)"
                        class="inline-flex bg-white items-center justify-center rounded-md ring-offset-background focus-visible:outline-none disabled:pointer-events-none disabled:opacity-50 bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10 opacity-0 group-hover:opacity-100 transition-all duration-300">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
                            class="bi bi-chat-fill" viewBox="0 0 16 16">
                            <path
                                d="M8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6-.097 1.016-.417 2.13-.771 2.966-.079.186.074.394.273.362 2.256-.37 3.597-.938 4.18-1.234A9.06 9.06 0 0 0 8 15z" />
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    </div>
</main>


<script type="text/javascript" src='<c:url value="/resources/js/imageLoad.js"/>'></script>
</body>

</html>