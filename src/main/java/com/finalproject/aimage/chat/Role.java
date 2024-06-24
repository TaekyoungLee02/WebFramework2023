package com.finalproject.aimage.chat;

public enum Role
{
	SYSTEM
	{
		@Override
		public String toString()
		{
			return "system";
		}
	},
	ASSISTANT
	{
		@Override
		public String toString()
		{
			return "assistant";
		}
	},
	USER
	{
		@Override
		public String toString()
		{
			return "user";
		}
	}
}
