<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script> 
    <script type="text/javascript" src='<c:url value="/resources/js/chat.js"/>'></script>
</head>
<body>
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
        
        <div class="rounded-lg border bg-card text-card-foreground shadow-sm mx-auto my-10 w-full sm:w-[500px]" data-v0-t="card">
            <div class="flex flex-col rounded-t space-y-1.5 p-6 px-4 py-2 bg-gray-200">
                <div class="flex items-center">
                    <span class="relative flex shrink-0 overflow-hidden rounded-full w-10 h-10"
                        src="/placeholder.svg?height=50&amp;width=50">
                    </span>
                    <h2 class="ml-4 text-xl font-semibold">ChatBot</h2>
                </div>
            </div>
            <div class="p-4 h-[400px] overflow-y-auto bg-gray-400">
                <div class="flex flex-col space-y-4" id="chatForm"></div>
            </div>
            <div class="flex rounded-b items-center p-4 bg-gray-200">
                <div class="flex w-full space-x-4">
                    <input class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 flex-grow" placeholder="Type your message here..." id="message"/>
                    <button class="inline-flex items-center justify-center rounded-lg bg-purple-600 hover:bg-purple-700 text-white font-semibold ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2"
                        onclick="makeMyChat(document.getElementById('message'))">
                        Send
                    </button>
                </div>
            </div>
        </div>
    </main>

    <script type="text/javascript" src='<c:url value="/resources/js/onPageLoaded.js"/>'></script>
</body>
</html>