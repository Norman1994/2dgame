using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MovingPlatform : MonoBehaviour 
{

	private float speed = 2.0f;

	private Vector3 direction;

    public float upPoint;
    public float downPoint;

    public bool moveHorizontal;

	void Start()
	{
		direction = transform.up;
	}

	void Update()
	{
		Move ();
	}

	private void OnTriggerEnter2D (Collider2D collider)
	{
		if (collider.tag == "Trigger") 
		{
			Debug.Log ("Хуй");
			direction = transform.up;
			Debug.Log ("Вверх");
		}

		if (collider.tag == "Trigger2") 
		{
			Debug.Log ("Хуй2");
			direction  = -transform.up;
			Debug.Log ("Вниз");
		}
	}

	private void Move()
	{
		transform.position = Vector3.MoveTowards (transform.position, transform.position + direction, speed * Time.deltaTime);
	}
}
