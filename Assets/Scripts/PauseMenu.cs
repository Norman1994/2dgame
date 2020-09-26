﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PauseMenu : MonoBehaviour {

    public float timer;
    public bool ispuse;
    public bool guipuse;

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update ()
    {
        Time.timeScale = timer;
        if (Input.GetKeyDown(KeyCode.Escape) && ispuse == false)
        {
            ispuse = true;
        }
        else if (Input.GetKeyDown(KeyCode.Escape) && ispuse == true)
        {
            ispuse = false;
        }
        if (ispuse == true)
        {
            timer = 0;
            guipuse = true;

        }
        else if (ispuse == false)
        {
            timer = 1f;
            guipuse = false;
        }
    }

    public void OnGUI()
    {
        if (guipuse == true)
        {
            if (GUI.Button(new Rect((float)(Screen.width / 2), (float)(Screen.height / 2) - 150f, 150f, 45f), "Продолжить убивать"))
            {
                ispuse = false;
                timer = 0;
            }
            if (GUI.Button(new Rect((float)(Screen.width / 2), (float)(Screen.height / 2), 160f, 45f), "Выпустите меня отсюда"))
            {
                ispuse = false;
                timer = 0;
                Application.LoadLevel("Menu");
            }
        }
    }
        
}
