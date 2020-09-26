using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MovebleMonster : Monster 
{
	private float speed = 2.0f;

	private Vector3 direction;

	private bool isGrounded = true;

	private SpriteRenderer sprite;

	public static bool damage;

	protected override void Awake()
	{
		sprite = GetComponentInChildren<SpriteRenderer> ();
	}

	protected override void Start()
	{
		direction = transform.right;
		damage = false;
	}

	protected override void Update ()
	{
		Move ();
	}

	protected override void OnTriggerEnter2D (Collider2D collider)
	{
		Unit unit = collider.GetComponent<Unit> ();

		if (unit && unit is Character) 
		{
			if (Mathf.Abs (unit.transform.position.y - transform.position.y) < 0.9f) 
			{
				damage = true;
				ReceiveDamage ();
			}
			else unit.ReceiveDamage ();
		}

		if (collider.tag == "Trigger") 
		{
			direction *= -1.0F;
			sprite.flipX = true;
		}

		if (collider.tag == "Trigger2") 
		{
			direction *= -1.0F;
			sprite.flipX = false;
		}
	}

	private void Move()
	{
		transform.position = Vector3.MoveTowards (transform.position, transform.position + direction, speed * Time.deltaTime);
	}
}
