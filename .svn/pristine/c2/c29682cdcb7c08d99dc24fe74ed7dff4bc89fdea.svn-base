package com.a1magway.bgg.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class SDTimer
{

	private Timer mTimer = null;

	private Handler mHandler = new Handler(Looper.getMainLooper())
	{

		@Override
		public void handleMessage(Message msg)
		{
			SDTimerListener listener = (SDTimerListener) msg.obj;
			listener.onWorkMain();
			super.handleMessage(msg);
		}

	};

	private void sendRunMessage(SDTimerListener listener)
	{
		Message msg = mHandler.obtainMessage();
		msg.obj = listener;
		msg.what = 0;
		mHandler.sendMessage(msg);
	}

	public void startWork(long delay, final SDTimerListener listener)
	{
		newTimer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				if (listener != null)
				{
					listener.onWork();
					sendRunMessage(listener);
				}
			}
		}, delay);
	}

	public void startWork(Date when, final SDTimerListener listener)
	{
		newTimer().schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				if (listener != null)
				{
					listener.onWork();
					sendRunMessage(listener);
				}
			}
		}, when);
	}

	public void startWork(Date when, long period, final SDTimerListener listener)
	{
		newTimer().schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				if (listener != null)
				{
					listener.onWork();
					sendRunMessage(listener);
				}
			}
		}, when, period);
	}

	public void startWork(long delay, long period, final SDTimerListener listener)
	{
		newTimer().schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				if (listener != null)
				{
					listener.onWork();
					sendRunMessage(listener);
				}
			}
		}, delay, period);
	}

	private Timer newTimer()
	{
		stopWork();
		mTimer = new Timer();
		return mTimer;
	}

	public void stopWork()
	{
		if (mTimer != null)
		{
			mTimer.cancel();
		}
		if (mHandler != null)
		{
			mHandler.removeMessages(0);
		}
	}

	public interface SDTimerListener
	{
		public void onWork();

		public void onWorkMain();
	}

}
