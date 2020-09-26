using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PauseMenuScript : MonoBehaviour 
{

	public static bool isPaused = false;
	public GameObject pauseMenuUI;
	
	void Start()
	{
		Time.timeScale = 1f;
	}

	void Update () 
	{
		
		if (Input.GetKeyDown (KeyCode.Escape)) 
		{
			if (isPaused) 
			{
				Resume ();
			} 
			else 
			{
				Pause ();
			}
		}
		
	}

	void Resume()
	{
		pauseMenuUI.SetActive (false);
		Time.timeScale = 1f;
		isPaused = false;
	}

	void Pause()
	{
		pauseMenuUI.SetActive (true);
		Time.timeScale = 0f;
		isPaused = true;
	}

	public void LoadGame()
	{
		Resume ();
	}

	public void QuitGame()
	{
		Application.LoadLevel ("Menu");
	}
}
